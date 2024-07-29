package com.elifgenc.service.data.repository;

import com.elifgenc.service.data.entity.ToDoItem;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {

    List<ToDoItem> findByUserId(Long userId);
}
