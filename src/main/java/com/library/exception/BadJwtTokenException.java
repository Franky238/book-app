package com.library.exception;

import javax.servlet.ServletException;

public class BadJwtTokenException extends ServletException {
    public BadJwtTokenException() {
        super();
    }

    public BadJwtTokenException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BadJwtTokenException(String message) {
        super(message);
    }

    public BadJwtTokenException(Throwable throwable) {
        super(throwable);
    }
}
