package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends GlobalApiException {
    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
