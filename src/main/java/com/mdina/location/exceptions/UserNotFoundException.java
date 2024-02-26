package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RecordNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
