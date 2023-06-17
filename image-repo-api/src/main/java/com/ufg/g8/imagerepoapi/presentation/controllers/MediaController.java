package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/images")
public class MediaController {

    @Autowired
    private IMediaService mediaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void create(@RequestBody @Valid MediaDto mediaDto) {
        this.mediaService.create(mediaDto);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private MediaDto read(@PathVariable(name = "id") ObjectId id) {
        return this.mediaService.read(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void update(@PathVariable(name = "id") ObjectId id, @RequestBody @Valid MediaDto mediaDto) {
        this.mediaService.update(id, mediaDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void delete(@PathVariable(name = "id") ObjectId id) {
        this.mediaService.delete(id);
    }

}
