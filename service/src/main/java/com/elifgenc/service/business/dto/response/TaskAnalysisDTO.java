package com.elifgenc.service.business.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAnalysisDTO {
    private String date;
    private long addedTasks;
    private long updatedTasks;
    private long totalTasks;
    private long doneTasks;
}
