package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends GlobalApiException {
    public RecordNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
