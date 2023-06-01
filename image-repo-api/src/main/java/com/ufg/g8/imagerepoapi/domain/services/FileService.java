package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.File;
import com.ufg.g8.imagerepoapi.domain.repositories.FileRepository;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.FileIOException;
import com.ufg.g8.imagerepoapi.presentation.dtos.FileDto;
import com.ufg.g8.imagerepoapi.presentation.services.IFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.Optional;

@Service
public class FileService implements IFileService {

    private static final String DEFAULT_FILE_TYPE = "image";

    private static final double RESIZE_AND_COMPRESS_RATIO = 0.7;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDto create(MultipartFile file) {
        String fileType = Optional.ofNullable(file.getContentType()).orElse(DEFAULT_FILE_TYPE);
        File newFile = this.createFile(file, fileType);
        newFile = this.fileRepository.save(newFile);
        return createFileDto(newFile);
    }

    private File createFile(MultipartFile file, String fileType) {
        try {
            this.checkFileType(fileType);
            BufferedImage image = ImageIO.read(file.getInputStream());
            BufferedImage resizedImage = resizeImage(image);
            byte[] imageData = this.compressImage(resizedImage);
            File fileEntity = new File();
            fileEntity.setName(file.getName());
            fileEntity.setSize(file.getSize());
            fileEntity.setType(fileType);
            fileEntity.setData(imageData);
            return fileEntity;
        } catch (IOException exception) {
            throw new FileIOException("Erro ao salvar o arquivo da imagem");
        }
    }

    private FileDto createFileDto(File file) {
        FileDto fileDto = new FileDto();
        BeanUtils.copyProperties(file, fileDto);
        fileDto.setBase64(Base64.getEncoder().encodeToString(file.getData()));
        return fileDto;
    }

    private void checkFileType(String fileType) {
        if(!fileType.contains(DEFAULT_FILE_TYPE))
            throw new InvalidParameterException("Arquivos " + fileType + " não são suportados");
    }

    private BufferedImage resizeImage(BufferedImage image) {
        int newWidth = (int)(image.getWidth() * RESIZE_AND_COMPRESS_RATIO);
        int newHeight = (int)(image.getHeight() * RESIZE_AND_COMPRESS_RATIO);
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        graphics.dispose();
        return resizedImage;
    }

    private byte[] compressImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        writer.setOutput(imageOutputStream);
        ImageWriteParam params = writer.getDefaultWriteParam();
        params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        params.setCompressionQuality((float)RESIZE_AND_COMPRESS_RATIO);
        writer.write(null, new IIOImage(image, null, null), params);
        byte[] compressedData = outputStream.toByteArray();
        outputStream.close();
        imageOutputStream.close();
        writer.dispose();
        return compressedData;
    }

}
