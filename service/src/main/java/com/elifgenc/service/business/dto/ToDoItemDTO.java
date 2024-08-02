package com.elifgenc.service.business.dto;

import com.elifgenc.service.audit.AuditingAwareBaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ToDoItemDTO extends AuditingAwareBaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String item;
    private Boolean isDone;
}
