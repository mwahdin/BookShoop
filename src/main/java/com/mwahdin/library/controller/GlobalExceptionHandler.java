package com.mwahdin.library.controller;

import com.mwahdin.library.dto.request.ExceptionResponse;
import com.mwahdin.library.exception.BookAlreadyExistsException;
import com.mwahdin.library.exception.BookNotFoundException;
import com.mwahdin.library.exception.BookValidationException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(methodArgumentNotValidExceptionToExceptionResponse(methodArgumentNotValidException));
    }
    private List<ExceptionResponse> methodArgumentNotValidExceptionToExceptionResponse(MethodArgumentNotValidException methodArgumentNotValidException){
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

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<List<ExceptionResponse>> handleRuleException (RuleException ruleException){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonList(ruleExceptionToExceptionResponse(ruleException)));
    }
    private ExceptionResponse ruleExceptionToExceptionResponse(RuleException ruleException){
        return ExceptionResponse.builder()
                .message(messageSourceAccessor.getMessage(ruleException.getMessage()))
                .field(messageSourceAccessor.getMessage(ruleException.getMessage()))
                .build();
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<List<ExceptionResponse>> handleBookNotFoundException (BookNotFoundException bookNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonList(bookNotFoundExceptionToExceptionResponse(bookNotFoundException)));
    }

    private ExceptionResponse bookNotFoundExceptionToExceptionResponse (BookNotFoundException bookNotFoundException){
        return ExceptionResponse.builder()
                .message(messageSourceAccessor.getMessage(bookNotFoundException.getMessage()))
                .field(messageSourceAccessor.getMessage(bookNotFoundException.getMessage()))
                .build();
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<List<ExceptionResponse>> handleBookAlreadyExistsException (BookAlreadyExistsException bookAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonList(bookAlreadyExistsExceptionToExceptionResponse(bookAlreadyExistsException)));
    }
    private ExceptionResponse bookAlreadyExistsExceptionToExceptionResponse(BookAlreadyExistsException bookAlreadyExistsException){
        return ExceptionResponse.builder()
                .message(messageSourceAccessor.getMessage(bookAlreadyExistsException.getMessage()))
                .field(messageSourceAccessor.getMessage(bookAlreadyExistsException.getMessage()))
                .build();
    }

    @ExceptionHandler(BookValidationException.class)
    public ResponseEntity<List<ExceptionResponse>> handleBookValidationException(BookValidationException bookValidationException){
        return ResponseEntity.status(400)
                .body(Collections.singletonList(bookValidationExceptionToExceptionResponse(bookValidationException)));
    }
    private ExceptionResponse bookValidationExceptionToExceptionResponse (BookValidationException bookValidationException){
        return ExceptionResponse.builder()
                .message(messageSourceAccessor.getMessage(bookValidationException.getMessage()))
                .field(messageSourceAccessor.getMessage(bookValidationException.getMessage()))
                .build();
    }


}