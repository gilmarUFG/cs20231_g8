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
@Document(collection = "tags")
public class Tag extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String color;

    @DBRef
    private List<Category> categories;

}
