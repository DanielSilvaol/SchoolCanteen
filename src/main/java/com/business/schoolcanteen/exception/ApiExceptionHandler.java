package com.business.schoolcanteen.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserExistsException.class })
    public ResponseEntity<Object> handleUserExistsException(UserExistsException ex, WebRequest request) {
        Error error = new Error("Usuário já existente", ExceptionUtils.getRootCauseMessage(ex));
        logger.error(error,ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        logger.error(error,ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ IOException.class })
    public ResponseEntity<Object> handleIOException(IOException ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new Error(fieldName, errorMessage));
        });
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Getter
    @Setter
    public static class Error {
        private String message;
        private String exceptionMessage;

        public Error(String message, String exceptionMessage) {
            this.message = message;
            this.exceptionMessage = exceptionMessage;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "message='" + message + '\'' +
                    ", exceptionMessage='" + exceptionMessage + '\'' +
                    '}';
        }
    }
}