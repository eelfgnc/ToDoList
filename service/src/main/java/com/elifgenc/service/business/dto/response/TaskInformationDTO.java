package com.elifgenc.service.business.dto.response;

import com.elifgenc.service.audit.AuditingAwareBaseDTO;
import com.elifgenc.service.data.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Task Information DTO", description = "Contains task information.")
public class TaskInformationDTO extends AuditingAwareBaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "It is the id of the task.", example = "14847")
    private Long id;

    @Schema(name = "item", description = "It is the description of the task.", example = "Makaleyi tamamla!!")
    private String item;

    @Schema(name = "isDone", description = "It is the completion information of the task.", example = "true")
    private Boolean isDone;

    @Schema(name = "isDelete", description = "It is the deletion information of the task.", example = "true")
    private Boolean isDelete;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(name = "createDate", description = "It is the creation date of that user.", example = "01-08-2024 10:00:00")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(name = "updateDate", description = "It is the update date of that user.", example = "03-08-2024 10:00:00")
    private LocalDateTime updateDate;

    public static TaskInformationDTO fromTask(Task task) {
        return TaskInformationDTO
                .builder()
                .id(task.getId())
                .item(task.getItem())
                .isDone(task.getIsDone())
                .isDelete(task.getIsDeleted())
                .createDate(task.getCreateDate())
                .updateDate(task.getUpdateDate())
                .build();
    }
}
