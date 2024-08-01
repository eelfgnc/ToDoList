package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.CreateToDoItemDTO;
import com.elifgenc.service.business.dto.ToDoItemDTO;
import java.util.List;

public interface ToDoItemService {
    List<ToDoItemDTO> getAllToDo(Long id);

    void createToDo(CreateToDoItemDTO createToDoItemDTO);

    void updateToDo(Long id, CreateToDoItemDTO createToDoItemDTO);

    void deleteToDo(Long id);
}
