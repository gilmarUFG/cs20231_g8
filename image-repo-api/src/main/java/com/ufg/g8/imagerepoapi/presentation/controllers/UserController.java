package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.presentation.dtos.TokenDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import com.ufg.g8.imagerepoapi.presentation.services.IUserService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto login(Authentication authentication) {
        return this.userService.login(authentication);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UserDto userDto) {
        this.userService.create(userDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto read(@PathVariable(name = "id")ObjectId id) {
        return this.userService.read(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto findAuthenticated() {
        return this.userService.findAuthenticated();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "id")ObjectId id, @RequestBody @Valid UserDto userDto) {
        this.userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id")ObjectId id) {
        this.userService.delete(id);
    }

}