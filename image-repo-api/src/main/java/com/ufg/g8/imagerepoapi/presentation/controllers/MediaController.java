package com.ufg.g8.imagerepoapi.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/images")
public class MediaController {

    @GetMapping
    public ResponseEntity<String> getById() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World");
    }

}
