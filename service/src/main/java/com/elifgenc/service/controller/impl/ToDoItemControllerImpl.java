package com.elifgenc.service.controller.impl;

import com.elifgenc.service.constant.ResponseConstant;
import com.elifgenc.service.controller.ToDoItemController;
import com.elifgenc.service.business.dto.CreateToDoItemDTO;
import com.elifgenc.service.business.dto.SuccessResponseDTO;
import com.elifgenc.service.business.dto.ToDoItemDTO;
import com.elifgenc.service.business.services.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoItemControllerImpl implements ToDoItemController {
    private final ToDoItemService toDoItemService;


    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ToDoItemDTO>> getToDoItems(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(toDoItemService.getAllToDo(id));
    }

    @Override
    public ResponseEntity<ToDoItemDTO> createToDoItem(CreateToDoItemDTO createToDoItemDTO) {
        return ResponseEntity.ok(toDoItemService.createToDo(createToDoItemDTO));
    }

    @Override
    public ResponseEntity<ToDoItemDTO> updateToDoItem(Long id, CreateToDoItemDTO updateToDoItemDTO) {
        return ResponseEntity.ok(toDoItemService.updateToDo(id, updateToDoItemDTO));
    }

    @Override
    public ResponseEntity<SuccessResponseDTO> deleteToDoItem(Long id) {
        toDoItemService.deleteToDo(id);
        return ResponseEntity.ok(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_DELETE_MESSAGE.getMessage()).build());

    }
}
