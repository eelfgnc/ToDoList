package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.request.TaskRequestDTO;
import com.elifgenc.service.business.dto.response.SuccessResponseDTO;
import com.elifgenc.service.business.dto.response.TaskAnalysisDTO;
import com.elifgenc.service.business.dto.response.TaskDTO;
import com.elifgenc.service.business.dto.response.TaskInformationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TaskController {
    @Operation(summary = "Lists tasks",
            description = "Lists the tasks belonging to the user according to the task type.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully lists the user's tasks according to task type.")
    })
    ResponseEntity<List<TaskDTO>> getTasksByUserIdAndType(Long userId, String type);

    @Operation(summary = "Lists history of tasks",
            description = "Lists history of tasks belonging to the user according to the task type.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully lists the user's history tasks according to task type.")
    })
    ResponseEntity<List<TaskInformationDTO>> getHistoryTasks(Long userId);

    @Operation(summary = "Creates a task",
            description = "Creates a task with the desired properties.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully creates a task.")
    })
    ResponseEntity<SuccessResponseDTO> createTask(TaskRequestDTO createToDoItemDTO);

    @Operation(summary = "Updated a task",
            description = "Updates a task with the desired properties.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a task.")
    })
    ResponseEntity<SuccessResponseDTO> updateTask(Long id, TaskRequestDTO updateToDoItemDTO);

    @Operation(summary = "Deletes a task",
            description = "Deletes a task by id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully mark a task as deleted.")
    })
    ResponseEntity<SuccessResponseDTO> deleteTask(Long id);

    @Operation(summary = "Deletes specific tasks",
            description = "Deletes specific tasks belonging to the user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marks tasks as deleted.")
    })
    ResponseEntity<SuccessResponseDTO> deleteAllTask( Long userId, String type);
}
