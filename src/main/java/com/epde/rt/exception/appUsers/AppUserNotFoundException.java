package com.epde.rt.exception.appUsers;

public class AppUserNotFoundException extends RuntimeException {
    public AppUserNotFoundException(Long userId) {
        super("Could Not Find User with ID: " + userId);
    }
}
