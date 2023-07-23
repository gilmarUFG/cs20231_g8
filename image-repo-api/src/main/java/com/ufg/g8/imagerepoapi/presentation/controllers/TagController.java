package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    @PostMapping("/{text}")
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto create(@PathVariable(name = "text") String text) {
        return this.tagService.create(text);
    }

}