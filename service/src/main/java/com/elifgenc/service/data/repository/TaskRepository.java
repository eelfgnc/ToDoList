package com.elifgenc.service.data.repository;

import com.elifgenc.service.data.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndIsDeletedFalse(Long userId);

    List<Task> findByUserIdAndIsDoneTrueAndIsDeletedFalse(Long userId);

    @Query(value = "select * from task as t where t.user_id = :userId order by t.create_date desc, t.update_date desc", nativeQuery = true)
    List<Task> findByUserId(@Param("userId") Long userId);
}
