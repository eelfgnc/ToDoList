package com.elifgenc.service.business.dto.response;

import com.elifgenc.service.audit.AuditingAwareBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Task DTO", description = "Contains task information.")
public class TaskDTO extends AuditingAwareBaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(name = "id", description = "It is the id of the task.", example = "14847")
    private Long id;
    @Schema(name = "item", description = "It is the description of the task.", example = "Makaleyi tamamla!!")
    private String item;
    @Schema(name = "isDone", description = "It is the completion information of the task.", example = "true")
    private Boolean isDone;
}
