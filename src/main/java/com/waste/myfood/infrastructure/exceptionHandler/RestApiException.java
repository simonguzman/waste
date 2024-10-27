package com.waste.myfood.infrastructure.exceptionHandler;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure.Error;
import com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure.ErrorCode;
import com.waste.myfood.infrastructure.exceptionHandler.exceptionStructure.ErrorUtils;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.BadFormatException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.BusinessRoleException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.EntityExistsException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.EntityNotFoundException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.NoDataException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.ObjectExistsException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.ObjectNotFoundException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.ObjectNullException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestApiException {
        @ExceptionHandler(BadFormatException.class)
        public ResponseEntity<Error> handleBadFormatException(final HttpServletRequest req,
                        final BadFormatException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.BAD_FORMAT.getCode(),
                                                String.format("%s, %s",
                                                                ErrorCode.BAD_FORMAT.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_ACCEPTABLE.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(EntityExistsException.class)
        public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                        final EntityExistsException ex) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.ENTITY_EXISTS.getCode(),
                                                String.format("%s, %s", ErrorCode.ENTITY_EXISTS.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.CONFLICT.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<Error> handleNotFoundException(final HttpServletRequest req,
                        final EntityNotFoundException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.ENTITY_NOT_FOUND.getCode(),
                                                String.format("%s, %s",
                                                                ErrorCode.ENTITY_NOT_FOUND.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_FOUND.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ObjectExistsException.class)
        public ResponseEntity<Error> handleObjectExistscException(final HttpServletRequest req,
                        final ObjectExistsException ex) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.OBJECT_EXISTS.getCode(),
                                                String.format("%s, %s", ErrorCode.OBJECT_EXISTS.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_ACCEPTABLE.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(NoDataException.class)
        public ResponseEntity<Error> handleNoDataException(final HttpServletRequest req,
                        final NoDataException ex) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.NO_DATA.getCode(),
                                                String.format("%s, %s", ErrorCode.NO_DATA.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_FOUND.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ObjectNotFoundException.class)
        public ResponseEntity<Error> handleObjectNotFoundException(final HttpServletRequest req,
                        final ObjectNotFoundException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.OBJECT_NOT_FOUND.getCode(),
                                                String.format("%s, %s",
                                                                ErrorCode.OBJECT_NOT_FOUND.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_FOUND.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BusinessRoleException.class)
        public ResponseEntity<Error> handleBusinessRuleException(final HttpServletRequest req,
                        final BusinessRoleException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.BUSINESS_RULE_VIOLATION.getCode(),
                                                String.format("%s, %s",
                                                                ErrorCode.BUSINESS_RULE_VIOLATION.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_ACCEPTABLE.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(ObjectNullException.class)
        public ResponseEntity<Error> handleObjectNullException(final HttpServletRequest req,
                        final ObjectNullException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.OBJECT_NULL.getCode(),
                                                String.format("%s, %s",
                                                                ErrorCode.OBJECT_NULL.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.NOT_ACCEPTABLE.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
                        final Exception ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.GENERIC_ERROR.getCode(),
                                                ErrorCode.GENERIC_ERROR.getMessageKey(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Error> handleIllegalArgumentException(final HttpServletRequest req,
                        final IllegalArgumentException ex, final Locale locale) {
                final Error error = ErrorUtils
                                .createError(ErrorCode.BAD_FORMAT.getCode(),
                                                String.format("%s, %s",
                                                                ErrorCode.BAD_FORMAT.getMessageKey(),
                                                                ex.getMessage()),
                                                HttpStatus.BAD_REQUEST.value())
                                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
}
