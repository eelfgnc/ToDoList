package com.elifgenc.service.business.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Account lock information cannot be null")
    private Boolean isDeleted;
}
