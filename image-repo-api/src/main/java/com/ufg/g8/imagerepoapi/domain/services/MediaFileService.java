package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.MediaFile;
import com.ufg.g8.imagerepoapi.domain.repositories.MediaFileRepository;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.FileIOException;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaFileDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaFileService;
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
public class MediaFileService implements IMediaFileService {

    private static final String DEFAULT_FILE_TYPE = "image";

    private static final double RESIZE_AND_COMPRESS_RATIO = 0.7;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Override
    public MediaFileDto create(MultipartFile file) {
        String fileType = Optional.ofNullable(file.getContentType()).orElse(DEFAULT_FILE_TYPE);
        MediaFile newMediaFile = this.createFile(file, fileType);
        newMediaFile = this.mediaFileRepository.save(newMediaFile);
        return createFileDto(newMediaFile);
    }

    private MediaFile createFile(MultipartFile file, String fileType) {
        try {
            this.checkFileType(fileType);
            BufferedImage image = ImageIO.read(file.getInputStream());
            BufferedImage resizedImage = resizeImage(image);
            byte[] imageData = this.compressImage(resizedImage);
            MediaFile mediaFileEntity = new MediaFile();
            mediaFileEntity.setName(file.getName());
            mediaFileEntity.setSize(file.getSize());
            mediaFileEntity.setType(fileType);
            mediaFileEntity.setData(imageData);
            return mediaFileEntity;
        } catch (IOException exception) {
            throw new FileIOException("Erro ao salvar o arquivo da imagem");
        }
    }

    private MediaFileDto createFileDto(MediaFile mediaFile) {
        MediaFileDto mediaFileDto = new MediaFileDto();
        BeanUtils.copyProperties(mediaFile, mediaFileDto);
        mediaFileDto.setBase64(Base64.getEncoder().encodeToString(mediaFile.getData()));
        return mediaFileDto;
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
