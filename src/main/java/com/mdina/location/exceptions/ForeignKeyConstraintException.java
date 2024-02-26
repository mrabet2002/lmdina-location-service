package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class ForeignKeyConstraintException extends GlobalApiException {
    public ForeignKeyConstraintException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
