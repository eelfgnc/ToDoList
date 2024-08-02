package com.elifgenc.service.data.repository;

import com.elifgenc.service.data.entity.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByUserIdAndIsDeletedFalse(Long userId);

    List<Task> findByUserIdAndIsDoneTrueAndIsDeletedFalse(Long userId);
}
