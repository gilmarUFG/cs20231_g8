package com.ufg.g8.imagerepoapi.presentation.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MediaDto {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Integer views;

    private Integer downloads;

    private String authorName;

    private ObjectId authorId;

    private List<ObjectId> tagsId;

    private List<TagDto> tags;

    private MediaFileDto file;

    private ObjectId fileId;

    private List<ReportDto> reports;

    private Date updatedAt;

    private Date createdAt;

}
