package com.library.exception;

public class PasswordNotMatchException extends Exception {
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(Throwable throwable) {
        super(throwable);
    }
}
