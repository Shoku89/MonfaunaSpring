package com.monfauna.api.exception;

import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> notFoundException(NotFoundException e, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var path = request.getRequestURI();
        var body = new ApiResponse(status.value(), e.getMessage(), path);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InvalidResourceException.class)
    public ResponseEntity<ApiResponse> invalidResouceException(InvalidResourceException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var path = request.getRequestURI();
        var body = new ApiResponse(status.value(), e.getMessage(), path);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(BusinessRulesException.class)
    public ResponseEntity<ApiResponse> businessRulesException(BusinessRulesException e, HttpServletRequest request) {
        var status = HttpStatus.CONFLICT;
        var path = request.getRequestURI();
        var body = new ApiResponse(status.value(), e.getMessage(), path);
        return ResponseEntity.status(status).body(body);
    }
}
