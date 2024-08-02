package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.request.TaskRequestDTO;
import com.elifgenc.service.business.dto.response.SuccessResponseDTO;
import com.elifgenc.service.business.dto.response.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskController {
    ResponseEntity<List<TaskDTO>> getToDoItems(Long userId, String type);

    ResponseEntity<SuccessResponseDTO> createToDoItem(TaskRequestDTO createToDoItemDTO);

    ResponseEntity<SuccessResponseDTO> updateToDoItem(Long id, TaskRequestDTO updateToDoItemDTO);

    ResponseEntity<SuccessResponseDTO> deleteToDoItem(Long id);

    ResponseEntity<SuccessResponseDTO> deleteAllToDoItem( Long userId, String type);
}
