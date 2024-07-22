package com.elifgenc.service.exception;

import com.elifgenc.service.constant.ErrorMessage;

public class RefreshTokenException extends ToDoServiceException{

    public RefreshTokenException(ErrorMessage errorMessage, Exception exception) {
        super(errorMessage, exception);
    }

    public RefreshTokenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
