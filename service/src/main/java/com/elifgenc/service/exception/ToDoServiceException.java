package com.elifgenc.service.exception;

import com.elifgenc.service.constant.ErrorMessage;

public class ToDoServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String internalMessage;

    public ToDoServiceException(ErrorMessage errorMessage, Exception exception) {
        super(errorMessage.getMessage(), exception);
        this.code = errorMessage.getCode();
        this.internalMessage = errorMessage.getInternalMessage();
    }

    public ToDoServiceException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.code = errorMessage.getCode();
        this.internalMessage = errorMessage.getInternalMessage();
    }

    public String getCode() {
        return code;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

}
