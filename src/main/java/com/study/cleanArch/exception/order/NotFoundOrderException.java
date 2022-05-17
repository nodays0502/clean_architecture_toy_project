package com.study.cleanArch.exception.order;

public class NotFoundOrderException  extends RuntimeException{

    public NotFoundOrderException() {
    }

    public NotFoundOrderException(String message) {
        super(message);
    }
}
