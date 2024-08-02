package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.bean.ModelMapperBean;
import com.elifgenc.service.business.services.TaskService;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.business.dto.CreateToDoItemDTO;
import com.elifgenc.service.business.dto.ToDoItemDTO;
import com.elifgenc.service.data.entity.Task;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.data.repository.TaskRepository;
import com.elifgenc.service.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository toDoItemRepository;
    private final ModelMapperBean modelMapperBean;

    @Override
    public List<ToDoItemDTO> getAllToDo(Long id, String type) {
        List<Task> toDoItems = toDoItemRepository.findByUserIdAndIsDeletedFalse(id);
        List<Task> resultToDoItems = new ArrayList<>();
        if("All".equals(type)){
            return toDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, ToDoItemDTO.class)).collect(Collectors.toList());
        }else if("Done".equals(type)){
            resultToDoItems.addAll(toDoItems.stream().filter(Task::getIsDone).toList());
            return resultToDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, ToDoItemDTO.class)).collect(Collectors.toList());
        }else if("Todo".equals(type)){
            resultToDoItems.addAll(toDoItems.stream().filter(t -> !t.getIsDone()).toList());
            return resultToDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, ToDoItemDTO.class)).collect(Collectors.toList());
        }
        return toDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, ToDoItemDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void createToDo(CreateToDoItemDTO createToDoItemDTO) {
        System.out.println(createToDoItemDTO.getUserId());
        User user = userRepository.findById(createToDoItemDTO.getUserId()).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));

        Task toDoItem = Task.builder()
                .user(user)
                .item(createToDoItemDTO.getItem())
                .isDone(createToDoItemDTO.getCompleted())
                .isDeleted(false)
                .build();
        Task createToDoItem = toDoItemRepository.save(toDoItem);
        user.getToDoItems().add(createToDoItem);
        userRepository.save(user);
    }

    @Override
    public void updateToDo(Long id, CreateToDoItemDTO createToDoItemDTO) {
        Task toDoItem = toDoItemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.TODO_NOT_FOUND));
        toDoItem.setItem(createToDoItemDTO.getItem());
        toDoItem.setIsDone(createToDoItemDTO.getCompleted());
        toDoItem.setIsDeleted(false);
        toDoItemRepository.save(toDoItem);
    }

    @Override
    public void deleteToDo(Long id) {
        Task toDoItem = toDoItemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.TODO_NOT_FOUND));
        toDoItem.setIsDeleted(true);
        toDoItemRepository.save(toDoItem);
    }

    @Override
    public void deleteToDosByType( Long userId, String type) {
        List<Task> toDoItems = new ArrayList<>();
        if("All".equals(type)){
            toDoItems.addAll(toDoItemRepository.findByUserIdAndIsDeletedFalse(userId));
        }else if ("Done".equals(type)){
            toDoItems.addAll(toDoItemRepository.findByUserIdAndIsDoneTrueAndIsDeletedFalse(userId));
        }
        for (Task toDoItem : toDoItems) {
            toDoItem.setIsDeleted(true);
            toDoItemRepository.save(toDoItem);
        }
    }
}
