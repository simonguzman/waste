package com.waste.myfood.infrastructure.output.formatter;

import com.waste.myfood.application.output.ExceptionFormatterIntPort;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.BadFormatException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.BusinessRoleException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.NoDataException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public class ExceptionFormatterImplAdapter implements ExceptionFormatterIntPort{

    @Override
    public void returnResponseErrorEntityExists(String message) {
        EntityExistsException objException = new EntityExistsException(message);
        throw objException;
    }

    @Override
    public void returnResponseErrorEntityNotFound(String message) {
        EntityNotFoundException objException = new EntityNotFoundException(message);
        throw objException;
    }

    @Override
    public void returnResponseBusinessRuleViolated(String message) {
        BusinessRoleException objException = new BusinessRoleException(message);
        throw objException;
    }

    @Override
    public void returnResponseBadFormat(String message) {
        BadFormatException objException = new BadFormatException(message);
        throw objException;
    }

    @Override
    public void returnNoData(String message) {
        NoDataException objException = new NoDataException(message);
        throw objException;
    }
    
}
