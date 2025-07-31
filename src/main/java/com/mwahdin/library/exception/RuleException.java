package com.mwahdin.library.exception;

import lombok.Getter;

@Getter
public class RuleException extends RuntimeException{
    public RuleException(String message) {
        super(message);
    }
}
