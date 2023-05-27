package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "images")
public class Image extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer views;

    @NotNull
    private Integer downloads;

    @DBRef
    private User author;

    @DBRef
    private File file;

    @DBRef
    private List<Category> categories;

}
