package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.presentation.dtos.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IFileService {

    FileDto create(MultipartFile file);

}
