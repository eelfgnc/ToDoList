package com.elifgenc.service.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateToDoItemDTO {
    private Long userId;
    private Long todoItemId;
    private String description;
    private LocalDateTime dueTime;
}
