package com.elifgenc.service.exception;

import com.elifgenc.service.constant.ErrorMessage;

public class ConstraintViolationException extends ToDoServiceException{

    public ConstraintViolationException(ErrorMessage errorMessage, Exception exception) {
        super(errorMessage, exception);
    }

    public ConstraintViolationException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
