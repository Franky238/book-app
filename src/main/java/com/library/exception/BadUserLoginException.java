package com.library.exception;

public class BadUserLoginException extends Exception {
    public BadUserLoginException() {
        super();
    }

    public BadUserLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BadUserLoginException(String message) {
        super(message);
    }

    public BadUserLoginException(Throwable throwable) {
        super(throwable);
    }
}
