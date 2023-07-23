package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.dtos.EnumDto;
import com.ufg.g8.imagerepoapi.presentation.services.IGenericEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Autowired
    private IGenericEnumService genericEnumService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String health() {
        return "Pixel Port API Running!";
    }

    @GetMapping("/enum/{enumType}")
    @ResponseStatus(HttpStatus.OK)
    public List<EnumDto> loadEnum(@PathVariable(name = "enumType") String enumType) {
        return this.genericEnumService.loadEnum(enumType);
    }

}