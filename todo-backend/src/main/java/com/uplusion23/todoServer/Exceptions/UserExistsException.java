package com.uplusion23.todoServer.Exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }
}
