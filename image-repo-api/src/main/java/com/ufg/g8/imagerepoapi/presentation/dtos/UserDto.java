package com.ufg.g8.imagerepoapi.presentation.dtos;

import com.ufg.g8.imagerepoapi.domain.models.MediaFile;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

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

    private MediaFile profilePicture;

    private List<ReportDto> reports;

    private Date updatedAt;

    private Date createdAt;
    
}
