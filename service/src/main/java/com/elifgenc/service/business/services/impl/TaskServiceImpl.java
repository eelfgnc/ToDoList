package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.bean.ModelMapperBean;
import com.elifgenc.service.business.dto.response.TaskAnalysisDTO;
import com.elifgenc.service.business.dto.response.TaskInformationDTO;
import com.elifgenc.service.business.services.TaskService;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.business.dto.request.TaskRequestDTO;
import com.elifgenc.service.business.dto.response.TaskDTO;
import com.elifgenc.service.data.entity.Task;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.data.repository.TaskRepository;
import com.elifgenc.service.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ModelMapperBean modelMapperBean;

    @Override
    public List<TaskDTO> getAllTask(Long id, String type) {
        List<Task> toDoItems = taskRepository.findByUserIdAndIsDeletedFalse(id);
        List<Task> resultToDoItems = new ArrayList<>();
        if("All".equals(type)){
            return toDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, TaskDTO.class)).collect(Collectors.toList());
        }else if("Done".equals(type)){
            resultToDoItems.addAll(toDoItems.stream().filter(Task::getIsDone).toList());
            return resultToDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, TaskDTO.class)).collect(Collectors.toList());
        }else if("Todo".equals(type)){
            resultToDoItems.addAll(toDoItems.stream().filter(t -> !t.getIsDone()).toList());
            return resultToDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, TaskDTO.class)).collect(Collectors.toList());
        }
        return toDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<TaskInformationDTO> getHistoryTasks(Long userId){
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(TaskInformationDTO::fromTask).collect(Collectors.toList());
    }

    @Override
    public void createTask(TaskRequestDTO taskRequestDTO) {
        System.out.println(taskRequestDTO.getUserId());
        User user = userRepository.findById(taskRequestDTO.getUserId()).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));

        Task toDoItem = Task.builder()
                .user(user)
                .item(taskRequestDTO.getItem())
                .isDone(taskRequestDTO.getCompleted())
                .isDeleted(false)
                .build();
        Task createToDoItem = taskRepository.save(toDoItem);
        user.getToDoItems().add(createToDoItem);
        userRepository.save(user);
    }

    @Override
    public void updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        Task toDoItem = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.TODO_NOT_FOUND));
        toDoItem.setItem(taskRequestDTO.getItem());
        toDoItem.setIsDone(taskRequestDTO.getCompleted());
        toDoItem.setIsDeleted(false);
        taskRepository.save(toDoItem);
    }

    @Override
    public void deleteTask(Long id) {
        Task toDoItem = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.TODO_NOT_FOUND));
        toDoItem.setIsDeleted(true);
        taskRepository.save(toDoItem);
    }

    @Override
    public void deleteTaskByUserIdAndType( Long userId, String type) {
        List<Task> toDoItems = new ArrayList<>();
        if("All".equals(type)){
            toDoItems.addAll(taskRepository.findByUserIdAndIsDeletedFalse(userId));
        }else if ("Done".equals(type)){
            toDoItems.addAll(taskRepository.findByUserIdAndIsDoneTrueAndIsDeletedFalse(userId));
        }
        for (Task toDoItem : toDoItems) {
            toDoItem.setIsDeleted(true);
            taskRepository.save(toDoItem);
        }
    }


}
