package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User extends BaseEntity {

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @DBRef
    private File file;

}
