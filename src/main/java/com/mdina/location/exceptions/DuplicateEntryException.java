package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateEntryException extends GlobalApiException{
    public DuplicateEntryException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
