package com.michelewm.desafiotodolist.services.exceptions;

public class ValidationTaskException extends RuntimeException{
    public ValidationTaskException(String message) {
        super(message);
    }
}
