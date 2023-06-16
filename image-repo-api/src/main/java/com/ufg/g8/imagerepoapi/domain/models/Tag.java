package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import com.ufg.g8.imagerepoapi.infrastructure.enums.TagColors;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
    private TagColors color;

    @DBRef
    private List<Category> categories = new ArrayList<>();

    public Tag(String tag, TagColors color) {
        this.setTag(tag);
        this.setColor(color);
    }

}
