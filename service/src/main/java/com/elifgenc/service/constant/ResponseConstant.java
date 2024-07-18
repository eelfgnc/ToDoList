package com.elifgenc.service.constant;

public enum ResponseConstant {
    SUCCESS_DELETE_MESSAGE("Başarıyla silindi.");

    private String message;

    ResponseConstant(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
