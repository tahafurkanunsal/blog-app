package com.tahafurkan.sandbox.blogapplication.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public BlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
