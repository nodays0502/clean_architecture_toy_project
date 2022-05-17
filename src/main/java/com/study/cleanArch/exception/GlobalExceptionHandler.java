package com.study.cleanArch.exception;

import static com.study.cleanArch.exception.ExceptionResponseConstants.*;

import com.study.cleanArch.exception.member.DuplicateEmailException;
import com.study.cleanArch.exception.member.NotFoundMemberException;
import com.study.cleanArch.exception.order.NotFoundOrderException;
import com.study.cleanArch.exception.order.OrderIsDepart;
import com.study.cleanArch.exception.product.NotFoundProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // Valid 조건을 만족하지 못한 요청에 대한 에러 핸들러
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        log.debug("Validation failed", ex);
        return VALIDATION_FAILED;
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity handleUserDuplicateEmailException(
        DuplicateEmailException ex) {
        log.debug("중복 Email", ex);
        return DUPLICATION_EMAIL;
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public final ResponseEntity handleNotFoundMemberException(
        NotFoundMemberException ex) {
        log.debug("회원 없음", ex);
        return NOT_FOUND_MEMBER;
    }

    @ExceptionHandler(NotFoundOrderException.class)
    public final ResponseEntity handleNotFoundOrderException(
        NotFoundOrderException ex) {
        log.debug("주문 없음", ex);
        return NOT_FOUND_ORDER;
    }

    @ExceptionHandler(OrderIsDepart.class)
    public final ResponseEntity handleOrderIsDepart(
        OrderIsDepart ex) {
        log.debug("주문이 벌써 출발함", ex);
        return ORDER_IS_DEPART;
    }

    @ExceptionHandler(NotFoundProductException.class)
    public final ResponseEntity handleNotFoundProductException(
        NotFoundProductException ex) {
        log.debug("상품 없음", ex);
        return NOT_FOUND_PRODUCT;
    }
}
