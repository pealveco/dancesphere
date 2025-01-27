package com.dancesphere.dancer.application.exception;

public class DancerNotFoundException extends RuntimeException {
    public DancerNotFoundException(String message) {
        super(message);
    }
}