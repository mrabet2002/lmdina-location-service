package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends GlobalApiException {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
