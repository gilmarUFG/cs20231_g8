package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "audit")
public class Audit extends BaseEntity {

    private String entity;

    private ObjectId entityId;

    private ActionType action;

    @DBRef
    private User user;

    private String json;

    public Audit(String entity, ObjectId entityId, ActionType action, User user, String json) {
        this.entity = entity;
        this.entityId = entityId;
        this.action = action;
        this.user = user;
        this.json = json;
    }

}