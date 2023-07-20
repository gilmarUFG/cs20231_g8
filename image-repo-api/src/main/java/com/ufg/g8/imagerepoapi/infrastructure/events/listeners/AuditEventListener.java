package com.ufg.g8.imagerepoapi.infrastructure.events.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufg.g8.imagerepoapi.infrastructure.events.AuditEvent;
import com.ufg.g8.imagerepoapi.presentation.services.IAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventListener implements ApplicationListener<AuditEvent> {

    @Autowired
    private IAuditService auditService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(AuditEvent event) {
        try {
            auditService.audit(
                    event.getEntity(),
                    event.getEntityId(),
                    event.getAction(),
                    objectMapper.writeValueAsString(event.getJson())
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}