package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.CreateToDoItemDTO;
import com.elifgenc.service.business.dto.SuccessResponseDTO;
import com.elifgenc.service.business.dto.ToDoItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoItemController {
    ResponseEntity<List<ToDoItemDTO>> getToDoItems(Long userId);

    ResponseEntity<ToDoItemDTO> createToDoItem(CreateToDoItemDTO createToDoItemDTO);

    ResponseEntity<ToDoItemDTO> updateToDoItem(Long id, CreateToDoItemDTO updateToDoItemDTO);

    ResponseEntity<SuccessResponseDTO> deleteToDoItem(Long id);
}
