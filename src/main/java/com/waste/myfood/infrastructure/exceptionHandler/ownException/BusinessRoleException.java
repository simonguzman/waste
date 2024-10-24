package com.waste.myfood.infrastructure.exceptionHandler.ownException;

import com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessRoleException extends RuntimeException{

    private final String messageKey;
    private final String code;

    public BusinessRoleException(ErrorCode code){
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public BusinessRoleException(final String message){
        super(message);
        this.messageKey = ErrorCode.BUSINESS_RULE_VIOLATION.getMessageKey();
        this.code = ErrorCode.BUSINESS_RULE_VIOLATION.getCode();
    }
}
