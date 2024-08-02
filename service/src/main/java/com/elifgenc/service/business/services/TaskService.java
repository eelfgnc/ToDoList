package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.request.TaskRequestDTO;
import com.elifgenc.service.business.dto.response.TaskDTO;
import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllToDo(Long id, String type);

    void createToDo(TaskRequestDTO taskRequestDTO);

    void updateToDo(Long id, TaskRequestDTO taskRequestDTO);

    void deleteToDo(Long id);

    void deleteToDosByType( Long userId, String type);
}
