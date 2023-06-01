package com.ufg.g8.imagerepoapi.presentation.dtos;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
public class FileDto {

    private String id;

    private String name;

    private long size;

    private String type;

    private String base64;

    private Date updatedAt;

    private Date createdAt;

}
