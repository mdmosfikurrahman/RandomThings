package com.epde.rt.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("Could Not Find User with ID: " + userId);
    }
}
