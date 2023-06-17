package com.ufg.g8.imagerepoapi.presentation.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TagDto {

    private String id;

    @NotNull
    private String tag;

    private String tagBackground;

    private String tagTextColor;

    private Date updatedAt;

    private Date createdAt;

}
