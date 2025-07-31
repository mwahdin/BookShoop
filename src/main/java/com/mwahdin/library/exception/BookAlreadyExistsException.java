package com.mwahdin.library.exception;

import lombok.Getter;

@Getter
public class BookAlreadyExistsException extends RuleException{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
