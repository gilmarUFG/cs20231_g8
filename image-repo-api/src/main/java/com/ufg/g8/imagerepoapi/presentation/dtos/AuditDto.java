package com.ufg.g8.imagerepoapi.presentation.dtos;

import com.ufg.g8.imagerepoapi.domain.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditDto {

    private String id;

    private String entity;

    private String entityId;

    private String action;

    private User user;

    private String json;

}