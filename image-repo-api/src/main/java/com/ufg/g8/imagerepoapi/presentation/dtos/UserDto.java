package com.ufg.g8.imagerepoapi.presentation.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private ObjectId profilePictureId;

    private Date updatedAt;

    private Date createdAt;
}
