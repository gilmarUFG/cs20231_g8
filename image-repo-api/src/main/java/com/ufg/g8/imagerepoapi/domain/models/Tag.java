package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag extends BaseEntity {
    private String name;
    private String color;
    private String user_name;

}
