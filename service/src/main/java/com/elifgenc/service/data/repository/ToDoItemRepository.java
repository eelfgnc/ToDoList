package com.elifgenc.service.data.repository;

import com.elifgenc.service.data.entity.ToDoItem;
import org.springframework.data.repository.CrudRepository;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
}
