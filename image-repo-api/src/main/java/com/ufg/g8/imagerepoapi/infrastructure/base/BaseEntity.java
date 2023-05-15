package com.ufg.g8.imagerepoapi.infrastructure.base;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @Id
    private ObjectId id;

    @LastModifiedDate
    private Date updatedAt;

    @CreatedDate
    private Date createdAt;

}
