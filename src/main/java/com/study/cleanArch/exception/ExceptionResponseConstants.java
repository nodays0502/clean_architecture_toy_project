package com.study.cleanArch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ExceptionResponseConstants {

    public static final ResponseEntity<Object> VALIDATION_FAILED =
        new ResponseEntity<>("Validation Failed.", HttpStatus.BAD_REQUEST);

    public static final ResponseEntity<String> DUPLICATION_EMAIL =
        new ResponseEntity<>("중복된 이메일입니다.", HttpStatus.CONFLICT);

    public static final ResponseEntity<String> NOT_FOUND_MEMBER =
        new ResponseEntity<>("회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    public static final ResponseEntity<String> NOT_FOUND_PRODUCT =
        new ResponseEntity<>("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    public static final ResponseEntity<String> NOT_FOUND_ORDER =
        new ResponseEntity<>("주문을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    public static final ResponseEntity<String> ORDER_IS_DEPART =
        new ResponseEntity<>("이미 출발한 상품입니다.", HttpStatus.CONFLICT);
}
