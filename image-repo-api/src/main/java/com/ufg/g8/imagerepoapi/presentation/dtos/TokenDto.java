package com.ufg.g8.imagerepoapi.presentation.dtos;

import lombok.Getter;

@Getter
public class TokenDto {

    private String token;

    public TokenDto(String token){
        this.token = token;
    }

}
