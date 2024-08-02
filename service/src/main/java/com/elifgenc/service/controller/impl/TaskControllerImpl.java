package com.elifgenc.service.controller.impl;

import com.elifgenc.service.constant.ResponseConstant;
import com.elifgenc.service.controller.TaskController;
import com.elifgenc.service.business.dto.request.TaskRequestDTO;
import com.elifgenc.service.business.dto.response.SuccessResponseDTO;
import com.elifgenc.service.business.dto.response.TaskDTO;
import com.elifgenc.service.business.services.TaskService;
import com.elifgenc.service.utils.frontend.ReactFrontend;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = ReactFrontend.REACT_FRONTEND_PORT_URL)
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Task", description = "This tag includes task adding, updating, deleting and task completion services.")
public class TaskControllerImpl implements TaskController {
    private final TaskService toDoItemService;


    @Override
    @GetMapping("/user/{id}")
    @Operation(summary = "Lists tasks",
            description = "Lists the tasks belonging to the user according to the task type."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully lists the user's tasks according to task type.")
    })
    public ResponseEntity<List<TaskDTO>> getToDoItems(@PathVariable(name = "id") Long id, @RequestParam(name = "type", required = true, defaultValue = "All") String type) {
        return ResponseEntity.ok(toDoItemService.getAllToDo(id, type));
    }

    @Override
    @PostMapping
    @Operation(summary = "Creates a task",
            description = "Creates a task with the desired properties."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully creates a task.")
    })
    public ResponseEntity<SuccessResponseDTO> createToDoItem(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        toDoItemService.createToDo(taskRequestDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_ADD_MESSAGE.getMessage()));
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Updated a task",
            description = "Updates a task with the desired properties."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a task.")
    })
    public ResponseEntity<SuccessResponseDTO> updateToDoItem(@PathVariable(name = "id")Long id, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        toDoItemService.updateToDo(id, taskRequestDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_EDIT_MESSAGE.getMessage()));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a task",
            description = "Deletes a task by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully mark a task as deleted.")
    })
    public ResponseEntity<SuccessResponseDTO> deleteToDoItem(@PathVariable(name = "id")Long id) {
        toDoItemService.deleteToDo(id);
        return ResponseEntity.ok(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_DELETE_MESSAGE.getMessage()).build());

    }

    @Override
    @DeleteMapping("/all/{id}")
    @Operation(summary = "Deletes specific tasks",
            description = "Deletes specific tasks belonging to the user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marks tasks as deleted.")
    })
    public ResponseEntity<SuccessResponseDTO> deleteAllToDoItem(@PathVariable(name = "id") Long userId, @RequestParam(name = "type", required = true, defaultValue = "All") String type) {
        toDoItemService.deleteToDosByType(userId, type);
        return ResponseEntity.ok(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_ALL_DELETE_MESSAGE.getMessage()).build());
    }
}
