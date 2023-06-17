package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.presentation.dtos.MediaFileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IMediaFileService {

    MediaFileDto create(MultipartFile file);

}
