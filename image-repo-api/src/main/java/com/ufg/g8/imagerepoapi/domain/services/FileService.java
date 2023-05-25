package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.File;
import com.ufg.g8.imagerepoapi.domain.repositories.FileRepository;
import com.ufg.g8.imagerepoapi.presentation.dtos.FileDto;
import com.ufg.g8.imagerepoapi.presentation.services.IFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.Optional;

@Service
public class FileService implements IFileService {

    private static final String DEFAULT_FILE_TYPE = "image";

    private static final String INVALID_INPUT = "Erro no envio do arquivo";

    private static final String INVALID_FILE_TYPE = "Tipo de arquivo inválido";

    @Autowired
    private FileRepository fileRepository;

    public FileDto create(MultipartFile file) {
        String fileType = Optional.ofNullable(file.getContentType()).orElse(DEFAULT_FILE_TYPE);
        checkFileType(fileType);
        File newFile = new File();
        newFile.setName(file.getName());
        newFile.setSize(file.getSize());
        newFile.setType(fileType);
        try {
            newFile.setData(file.getBytes());
        } catch (IOException exception) {
            throw new InvalidParameterException(INVALID_INPUT);
        }
        newFile = this.fileRepository.save(newFile);
        FileDto fileDto = new FileDto();
        BeanUtils.copyProperties(newFile, fileDto);
        fileDto.setBase64(Base64.getEncoder().encodeToString(newFile.getData()));
        return fileDto;
    }

    private void checkFileType(String fileType) {
        if(!fileType.contains(DEFAULT_FILE_TYPE))
            throw new InvalidParameterException(INVALID_FILE_TYPE);
    }

}
