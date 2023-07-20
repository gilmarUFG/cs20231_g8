package com.ufg.g8.imagerepoapi.presentation.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumDto {

    private String value;

    private String description;

    public EnumDto(String value, String description) {
        this.setValue(value);
        this.setDescription(description);
    }

}
