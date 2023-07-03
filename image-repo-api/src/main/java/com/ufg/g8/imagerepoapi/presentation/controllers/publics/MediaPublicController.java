package com.ufg.g8.imagerepoapi.presentation.controllers.publics;

import com.ufg.g8.imagerepoapi.domain.services.filters.MediaFilter;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/public/images")
public class MediaPublicController {
    @Autowired
    private IMediaService mediaService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private MediaDto read(@PathVariable(name = "id") ObjectId id) {
        return this.mediaService.read(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<MediaDto> readAll(MediaFilter filter) {
        return this.mediaService.readAll(filter);
    }
}
