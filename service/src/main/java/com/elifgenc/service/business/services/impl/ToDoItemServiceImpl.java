package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.bean.ModelMapperBean;
import com.elifgenc.service.business.services.ToDoItemService;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.business.dto.CreateToDoItemDTO;
import com.elifgenc.service.business.dto.ToDoItemDTO;
import com.elifgenc.service.data.entity.ToDoItem;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.data.repository.ToDoItemRepository;
import com.elifgenc.service.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoItemServiceImpl implements ToDoItemService {
    private final UserRepository userRepository;
    private final ToDoItemRepository toDoItemRepository;
    private final ModelMapperBean modelMapperBean;

    @Override
    public List<ToDoItemDTO> getAllToDo(Long id) {
        List<ToDoItem> toDoItems = toDoItemRepository.findByUserId(id);
        return toDoItems.stream().map(t -> modelMapperBean.GetModelMapper().map(t, ToDoItemDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void createToDo(CreateToDoItemDTO createToDoItemDTO) {
        System.out.println(createToDoItemDTO.getUserId());
        User user = userRepository.findById(createToDoItemDTO.getUserId()).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));

        ToDoItem toDoItem = ToDoItem.builder()
                .user(user)
                .item(createToDoItemDTO.getItem())
                .dueDate(createToDoItemDTO.getDueTime())
                .isDone(false)
                .build();
        ToDoItem createToDoItem = toDoItemRepository.save(toDoItem);
        user.getToDoItems().add(createToDoItem);
        userRepository.save(user);
    }

    @Override
    public void updateToDo(Long id, CreateToDoItemDTO createToDoItemDTO) {
        ToDoItem toDoItem = toDoItemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.TODO_NOT_FOUND));
        toDoItem.setItem(createToDoItemDTO.getItem());
        toDoItem.setDueDate(createToDoItemDTO.getDueTime());
        toDoItem.setIsDone(false);
        toDoItemRepository.save(toDoItem);
    }

    @Override
    public void deleteToDo(Long id) {
        toDoItemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.TODO_NOT_FOUND));
        toDoItemRepository.deleteById(id);
    }
}
