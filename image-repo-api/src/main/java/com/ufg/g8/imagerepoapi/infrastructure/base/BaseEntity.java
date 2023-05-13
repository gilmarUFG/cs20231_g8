package com.ufg.g8.imagerepoapi.infrastructure.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class BaseEntity {
    @Id
    private String id;
    @CreatedDate
    private String createdAt;
    private String updatedAt;


}
