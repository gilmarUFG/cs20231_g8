package com.ufg.g8.imagerepoapi.presentation.dtos;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MediaDto {

    private String id;

    private String name;

    private String description;

    private Integer views;

    private Integer downloads;

    private String authorName;

    private List<ObjectId> tagsId;

    private List<TagDto> tags;

    private MediaFileDto file;

    private ObjectId fileId;

    private Date updatedAt;

    private Date createdAt;

}
