package com.ufg.g8.imagerepoapi.domain.services.filters;

import org.bson.types.ObjectId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditFilter {

    private String entity;

    private ObjectId entityId;

    private ObjectId userId;

}