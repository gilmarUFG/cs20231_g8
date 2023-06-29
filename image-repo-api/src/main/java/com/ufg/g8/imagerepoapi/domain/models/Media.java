package com.ufg.g8.imagerepoapi.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "images")
public class Media extends BaseEntity {

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
    private MediaFile mediaFile;

    @DBRef
    private List<Category> categories = new ArrayList<>();

    @DBRef
    private List<MediaReport> reports;

}
