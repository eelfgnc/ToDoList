package com.elifgenc.service.constant;

public enum ErrorMessage {
    USER_NOT_FOUND("TODO-001", "Kullanıcı bulunamadı.", "Kullanıcıya ait kayıt bulunamadı."),
    TODO_NOT_FOUND("TODO-001", "Yapılacak iş bulunamadı.", "Yapılacak iş kaydı bulunamadı."),
    ENTITY_NOT_FOUND("TODO-002", "İstenilen varlık bulunamadı.", "İstenilen kayıt bulunamadı."),
    EXIST_VALUE_FOUND("TODO-003", "Eklenmek istenen varlık daha önce eklenilmiştir.", "Eklenmek istenen değer daha önce eklenilmiştir."),
    REFRESH_TOKEN_NOT_FOUND("TODO-100", "Yenileme anahtarı bulunamadı.", "Yenileme anahtarı bulunamadı."),
    REFRESH_TOKEN_NOT_VALID("TODO-101", "Yenileme anahtarı geçerliliğini yitirmiştir.", "Yenileme anahtarı geçerliliğini yitirmiştir.");

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
