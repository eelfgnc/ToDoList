package com.elifgenc.service.controller.impl;

import com.elifgenc.service.business.dto.response.TaskAnalysisDTO;
import com.elifgenc.service.business.dto.response.TaskInformationDTO;
import com.elifgenc.service.business.services.impl.TaskServiceImpl;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = ReactFrontend.REACT_FRONTEND_PORT_URL)
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Task", description = "This tag includes task adding, updating, deleting and task completion services.")
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;
    private final TaskServiceImpl taskServiceImpl;

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserIdAndType(@PathVariable(name = "id") Long id, @RequestParam(name = "type", required = true, defaultValue = "All") String type) {
        return ResponseEntity.ok(taskService.getAllTask(id, type));
    }

    @Override
    @GetMapping("/user/{userId}/history")
    public ResponseEntity<List<TaskInformationDTO>> getHistoryTasks(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(taskService.getHistoryTasks(userId));
    }

    @Override
    @PostMapping
    public ResponseEntity<SuccessResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        taskService.createTask(taskRequestDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_ADD_MESSAGE.getMessage()));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> updateTask(@PathVariable(name = "id")Long id, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        taskService.updateTask(id, taskRequestDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_EDIT_MESSAGE.getMessage()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> deleteTask(@PathVariable(name = "id")Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_DELETE_MESSAGE.getMessage()).build());
    }

    @Override
    @DeleteMapping("/all/{id}")
    public ResponseEntity<SuccessResponseDTO> deleteAllTask(@PathVariable(name = "id") Long userId, @RequestParam(name = "type", required = true, defaultValue = "All") String type) {
        taskService.deleteTaskByUserIdAndType(userId, type);
        return ResponseEntity.ok(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_ALL_DELETE_MESSAGE.getMessage()).build());
    }
}
