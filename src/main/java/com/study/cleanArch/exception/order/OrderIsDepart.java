package com.study.cleanArch.exception.order;

public class OrderIsDepart extends RuntimeException{

    public OrderIsDepart() {
    }

    public OrderIsDepart(String message) {
        super(message);
    }
}
