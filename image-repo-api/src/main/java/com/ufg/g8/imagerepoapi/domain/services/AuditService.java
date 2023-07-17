package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.Audit;
import com.ufg.g8.imagerepoapi.domain.models.User;
import com.ufg.g8.imagerepoapi.domain.repositories.AuditRepository;
import com.ufg.g8.imagerepoapi.domain.repositories.UserRepository;
import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.presentation.services.IAuditService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditService implements IAuditService {

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void audit(String entity, ObjectId entityId, ActionType action, String json) {
        this.auditRepository.save(
          new Audit(
            entity, entityId, action, this.findUserByAuthenticated(), json
          )
        );
    }

    private User findUserByAuthenticated() {
        return this.userRepository.findByLogin(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

}