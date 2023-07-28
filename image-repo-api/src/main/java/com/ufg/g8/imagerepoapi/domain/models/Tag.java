package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import com.ufg.g8.imagerepoapi.infrastructure.enums.TagColor;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "tags")
public class Tag extends BaseEntity {

    @NotNull
    private String tag;

    @NotNull
    private TagColor color;

    @NotNull
    private TagColor description;

    @DBRef
    private List<Category> categories = new ArrayList<>();

    public Tag(String tag, TagColor color) {
        this.setTag(tag);
        this.setColor(color);
    }

}
