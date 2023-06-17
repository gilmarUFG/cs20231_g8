package com.ufg.g8.imagerepoapi.presentation.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CredentialsDto {

    @NotNull
    private String login;

    @NotNull
    private String password;

}
