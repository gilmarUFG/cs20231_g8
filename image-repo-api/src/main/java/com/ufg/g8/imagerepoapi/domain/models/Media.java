package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Media extends BaseEntity {
    private String fileName;
    private int size;
    private String type;
}
