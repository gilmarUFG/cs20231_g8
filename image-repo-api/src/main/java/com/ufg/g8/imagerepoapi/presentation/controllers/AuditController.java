package com.ufg.g8.imagerepoapi.presentation.controllers;

import com.ufg.g8.imagerepoapi.domain.services.filters.AuditFilter;
import com.ufg.g8.imagerepoapi.presentation.dtos.AuditDto;
import com.ufg.g8.imagerepoapi.presentation.services.IAuditService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auditing")
public class AuditController {

    @Autowired
    private IAuditService auditService;

}