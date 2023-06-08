package com.ufg.g8.imagerepoapi.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ActionNotAllowedException extends RuntimeException {

    public static final String EXCEPTION_DEVELOPER_MESSAGE = "Exception thrown when there was an attempt to access a resource without valid authentication";

    public ActionNotAllowedException(String message){
        super(message);
    }

}
