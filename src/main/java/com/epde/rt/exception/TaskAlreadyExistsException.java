package com.epde.rt.exception;

public class TaskAlreadyExistsException extends RuntimeException {

    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}