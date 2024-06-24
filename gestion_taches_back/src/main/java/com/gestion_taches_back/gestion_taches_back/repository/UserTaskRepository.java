package com.gestion_taches_back.gestion_taches_back.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion_taches_back.gestion_taches_back.entity.UserTask;

public interface UserTaskRepository extends JpaRepository<UserTask,Long> {

    List<UserTask> findByUserId(final Long userId);

    @Query("SELECT userTask FROM UserTask userTask WHERE userTask.userId=?1 ORDER BY DATE(userTask.dueDate) ASC")
    List<UserTask> sortUserTaskList(final Long userId);
}
