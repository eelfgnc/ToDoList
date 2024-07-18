package com.elifgenc.service.exception;

import com.elifgenc.service.constant.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends ToDoServiceException {
    public ObjectNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public ObjectNotFoundException(ErrorMessage errorMessage, Exception exception) {
        super(errorMessage, exception);
    }
}
