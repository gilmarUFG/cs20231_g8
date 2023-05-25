package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.dtos.FileDto;
import com.ufg.g8.imagerepoapi.presentation.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileDto create(@RequestParam("file") MultipartFile file) {
        return fileService.create(file);
    }

}
