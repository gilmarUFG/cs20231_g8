package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto create(@RequestBody String text) {
        return this.tagService.create(text);
    }

}
