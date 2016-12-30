package com.library.exception;

public class MimeTypeNotAllowedException extends Exception {
    public MimeTypeNotAllowedException() {
        super();
    }

    public MimeTypeNotAllowedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MimeTypeNotAllowedException(String message) {
        super(message);
    }

    public MimeTypeNotAllowedException(Throwable throwable) {
        super(throwable);
    }
}
