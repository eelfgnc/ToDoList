package com.elifgenc.service.constant;

import lombok.Getter;

@Getter
public enum RoleConstant {
    USER("User"),
    ADMIN("Admin");

    private String role;

    private RoleConstant(String role) {
        this.role = role;
    }
}
