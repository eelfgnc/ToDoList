package com.elifgenc.service.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
abstract public class AuditingAwareBaseDTO implements Serializable {
    public static final Long serialVersionUID=1L;

    @JsonIgnore
    protected String createdUser;

    protected LocalDateTime createdDate;

    @JsonIgnore
    protected String updateUser;

    protected LocalDateTime updateDate;
}