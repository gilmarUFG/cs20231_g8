package com.ufg.g8.imagerepoapi.infrastructure.base;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @Id
    @Field("_id")
    private ObjectId id;


    @LastModifiedDate
    private Date updatedAt;

    @CreatedDate
    private Date createdAt;

}
