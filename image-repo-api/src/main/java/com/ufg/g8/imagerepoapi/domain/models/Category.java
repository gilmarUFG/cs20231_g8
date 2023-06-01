package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "categories")
public class Category extends BaseEntity {

    @DBRef
    private Media media;

    @DBRef
    private Tag tag;

}
