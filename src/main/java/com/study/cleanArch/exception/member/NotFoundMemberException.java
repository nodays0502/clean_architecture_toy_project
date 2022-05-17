package com.study.cleanArch.exception.member;

public class NotFoundMemberException extends RuntimeException{

    public NotFoundMemberException() {
    }

    public NotFoundMemberException(String message) {
        super(message);
    }
}
