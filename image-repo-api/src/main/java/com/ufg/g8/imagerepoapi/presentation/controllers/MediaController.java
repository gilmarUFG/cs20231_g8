package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/images")
public class MediaController {

    @Autowired
    private IMediaService mediaService;

}
