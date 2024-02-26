package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends GlobalApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
