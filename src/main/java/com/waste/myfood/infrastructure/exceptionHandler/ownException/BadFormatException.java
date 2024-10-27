package com.waste.myfood.infrastructure.exceptionHandler.ownException;

import com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class BadFormatException extends RuntimeException {
    private final String mesageKey;
    private final String code;

    public BadFormatException(ErrorCode code) {
        super(code.getCode());
        this.mesageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public BadFormatException(final String message) {
        super(message);
        this.mesageKey = ErrorCode.BAD_FORMAT.getMessageKey();
        this.code = ErrorCode.BAD_FORMAT.getCode();
    }
}
