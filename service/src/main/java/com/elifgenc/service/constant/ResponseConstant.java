package com.elifgenc.service.constant;

public enum ResponseConstant {
    SUCCESS_DELETE_MESSAGE("The record was successfully deleted."),
    SUCCESS_ADD_MESSAGE("The record was successfully added."),
    SUCCESS_EDIT_MESSAGE("The record was successfully edited.");

    private String message;

    ResponseConstant(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
