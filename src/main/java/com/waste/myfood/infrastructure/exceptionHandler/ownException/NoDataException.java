package com.waste.myfood.infrastructure.exceptionHandler.ownException;

import com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class NoDataException extends RuntimeException{
    private final String messageKey;
    private final String code;

    public NoDataException(ErrorCode code){
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public NoDataException(final String message){
        super(message);
        this.messageKey = ErrorCode.NO_DATA.getMessageKey();
        this.code = ErrorCode.NO_DATA.getCode();
    }
}
