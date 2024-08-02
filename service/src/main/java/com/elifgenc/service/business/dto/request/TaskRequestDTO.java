package com.elifgenc.service.business.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Task Request DTO", description = "Contains task information.")
public class TaskRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "userId", description = "It is the id of the user.", example = "14847")
    private Long userId;
    @Schema(name = "item", description = "It is the description of the task.", example = "Makaleyi tamamla!!")
    private String item;
    @Schema(name = "completed", description = "It is the completion information of the task.", example = "true")
    private Boolean completed;
}