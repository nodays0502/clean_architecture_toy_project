package com.study.cleanArch.exception.member;


public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException() {
    }

    public DuplicateEmailException(String message) {
        super(message);
    }

}
