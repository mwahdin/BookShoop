package com.mwahdin.library.controller;

import com.mwahdin.library.dto.request.ExceptionResponse;
import com.mwahdin.library.exception.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSourceAccessor messageSourceAccessor;

    public GlobalExceptionHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor ;
    }


    @ExceptionHandler(RuleException.class)
    public ResponseEntity<List<ExceptionResponse>> handleRuleException (RuleException ruleException){

        return ResponseEntity.status(400)
                .body(Collections.singletonList(ruleExceptionToExceptionResponse(ruleException)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handeMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MethodArgumentNotValidExceptionToExceptionResponse(methodArgumentNotValidException));
    }



    private List<ExceptionResponse> MethodArgumentNotValidExceptionToExceptionResponse(MethodArgumentNotValidException methodArgumentNotValidException){
        return methodArgumentNotValidException
                .getFieldErrors()
                .stream().map(err ->
                        ExceptionResponse
                                .builder()
                                .message(err.getDefaultMessage())
                                .field(err.getField())
                                .build()
                ).collect(Collectors.toList());

    }
    private ExceptionResponse ruleExceptionToExceptionResponse(RuleException ruleException){
        return ExceptionResponse.builder()
                .message(messageSourceAccessor.getMessage(ruleException.getMessage()))
                .field(messageSourceAccessor.getMessage(ruleException.getMessage()))
                .build();
    }

}
