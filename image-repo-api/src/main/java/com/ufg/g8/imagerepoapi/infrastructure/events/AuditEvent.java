package com.ufg.g8.imagerepoapi.infrastructure.events;

import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEvent;

@Getter
public class AuditEvent extends ApplicationEvent {

    private String entity;

    private ObjectId entityId;

    private ActionType action;

    private Object json;

    public AuditEvent(Object source, Object json, String entity, ObjectId entityId, ActionType action) {
        super(source);
        this.entity = entity;
        this.entityId = entityId;
        this.action = action;
        this.json = json;
    }

    public AuditEvent(Object source, String entity, ObjectId entityId, ActionType action) {
        super(source);
        this.entity = entity;
        this.entityId = entityId;
        this.action = action;
        this.json = "";
    }

}