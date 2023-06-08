package com.ufg.g8.imagerepoapi.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileIOException extends RuntimeException {

    public static final String EXCEPTION_DEVELOPER_MESSAGE = "Exception thrown when there is an error while dealing with a file data, " +
                                                            "probably caused by currepted data, encrypted files or even empty files";

    public FileIOException(String message) {
        super(message);
    }

}
