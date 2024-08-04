package com.elifgenc.service.constant;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USER_NOT_FOUND("TODO-001", "Kullanıcı bulunamadı.", "Kullanıcıya ait kayıt bulunamadı."),
    TODO_NOT_FOUND("TODO-001", "Yapılacak iş bulunamadı.", "Yapılacak iş kaydı bulunamadı."),
    ENTITY_NOT_FOUND("TODO-002", "İstenilen varlık bulunamadı.", "İstenilen kayıt bulunamadı."),
    EXIST_VALUE_FOUND("TODO-003", "Eklenmek istenen varlık daha önce eklenilmiştir.", "Eklenmek istenen değer daha önce eklenilmiştir."),
    ROLE_NOT_FOUND("TODO-004", "Rol bulunamadı.", "Role ait kayıt bulunamadı."),
    EXPIRED_USER_TOKEN("TODO-100", "Giriş anahtarı geçersiz.", "Giriş anahtarı geçerliliğini kaydetmiştir."),
    REFRESH_TOKEN_NOT_FOUND("TODO-101", "Yenileme anahtarı bulunamadı.", "Yenileme anahtarı bulunamadı."),
    REFRESH_TOKEN_NOT_VALID("TODO-102", "Yenileme anahtarı geçerliliğini yitirmiştir.", "Yenileme anahtarı geçerliliğini yitirmiştir.");

    private final String code;
    private final String message;
    private final String internalMessage;

    ErrorMessage(String code, String message, String internalMessage) {
        this.code = code;
        this.message = message;
        this.internalMessage = internalMessage;
    }

}
