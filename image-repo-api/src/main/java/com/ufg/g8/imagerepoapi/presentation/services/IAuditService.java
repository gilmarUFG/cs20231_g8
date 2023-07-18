package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import com.ufg.g8.imagerepoapi.presentation.dtos.EnumDto;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAuditService {

    void audit(String entity, ObjectId entityId, ActionType action, String json);

}