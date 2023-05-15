package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "medias")
public class Media extends BaseEntity {

    @NotNull
    private String fileName;

    @NotNull
    private int size;

    @NotNull
    private String type;

}
