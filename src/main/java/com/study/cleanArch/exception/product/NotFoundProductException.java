package com.study.cleanArch.exception.product;

public class NotFoundProductException extends RuntimeException{

    public NotFoundProductException() {
    }

    public NotFoundProductException(String message) {
        super(message);
    }
}
