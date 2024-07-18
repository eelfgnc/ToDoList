package com.elifgenc.service.constant;

public enum ErrorMessage {
    ENTITY_NOT_FOUND("TODO-001", "İstenilen varlık buluanamadı.", "İstenilen kayıt bulunamadı."),
    EXIST_VALUE_FOUND("TODO-002", "Eklenmek istenen varlık daha önce eklenilmiştir.", "Eklenmek istenen değer daha önce eklenilmiştir.");

    private String code;
    private String message;
    private String internalMessage;

    ErrorMessage(String code, String message, String internalMessage) {
        this.code = code;
        this.message = message;
        this.internalMessage = internalMessage;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}
