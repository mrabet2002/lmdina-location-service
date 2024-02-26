package com.mdina.location.exceptions;

public class UsernameExistsException extends DuplicateEntryException {
    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException() {
        super("Username already exists");
    }
}
