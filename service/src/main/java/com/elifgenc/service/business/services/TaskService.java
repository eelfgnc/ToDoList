package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.request.TaskRequestDTO;
import com.elifgenc.service.business.dto.response.TaskAnalysisDTO;
import com.elifgenc.service.business.dto.response.TaskDTO;
import com.elifgenc.service.business.dto.response.TaskInformationDTO;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTask(Long id, String type);

    List<TaskInformationDTO> getHistoryTasks(Long userId);

    void createTask(TaskRequestDTO taskRequestDTO);

    void updateTask(Long id, TaskRequestDTO taskRequestDTO);

    void deleteTask(Long id);

    void deleteTaskByUserIdAndType( Long userId, String type);
}
