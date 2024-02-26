package com.mdina.location.exceptions;

import org.springframework.http.HttpStatus;

public class UploadFileException extends InternalServerErrorException {
    public UploadFileException(String message) {
        super(message);
    }

    public UploadFileException() {
        super("Error while uploading file");
    }
}
