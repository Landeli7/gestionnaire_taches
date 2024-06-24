package com.gestion_taches_back.gestion_taches_back.services.tasks;

import java.util.List;
import java.util.Optional;

import com.gestion_taches_back.gestion_taches_back.dto.UserTaskDto;
import com.gestion_taches_back.gestion_taches_back.entity.UserTask;

public interface TaskServiceInterface {

    List<UserTask> getUserTaskList(Long userId);
    List<UserTask> sortUserTaskList(Long userId);
    UserTaskDto createUserTask(UserTask newUserTask);
    void deleteUserTask(Long userTaskId);
    void completeUserTask(Optional<UserTask> userTaskToComplete);
    Boolean changePriorityUserTask(Long userTaskId, Integer priorite);
    Optional<UserTask> getUserTask(Long userTaskId);
}
