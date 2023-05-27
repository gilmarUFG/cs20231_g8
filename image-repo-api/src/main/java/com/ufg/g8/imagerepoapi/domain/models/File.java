package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "files")
public class File extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private long size;

    @NotNull
    private String type;

    @NotNull
    private byte[] data;

}
