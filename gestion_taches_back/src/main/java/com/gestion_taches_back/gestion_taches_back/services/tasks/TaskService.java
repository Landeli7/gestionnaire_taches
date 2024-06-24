package com.gestion_taches_back.gestion_taches_back.services.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion_taches_back.gestion_taches_back.dto.UserTaskDto;
import com.gestion_taches_back.gestion_taches_back.entity.UserTask;
import com.gestion_taches_back.gestion_taches_back.repository.UserTaskRepository;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Override
    @Transactional
    public List<UserTask> getUserTaskList(Long userId) {
        return userTaskRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public List<UserTask> sortUserTaskList(Long userId) {
        return userTaskRepository.sortUserTaskList(userId);
    }

    @Override
    @Transactional
    public UserTaskDto createUserTask(UserTask newUserTask) {
        UserTask createdUser = userTaskRepository.save(newUserTask);
        UserTaskDto userDto = new UserTaskDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    @Transactional
    public void deleteUserTask(Long userId) {
        Optional<UserTask> userTaskToDelete = userTaskRepository.findById(userId);
        userTaskRepository.delete(userTaskToDelete.get());
    }

    @Override
    @Transactional
    public Optional<UserTask> getUserTask(Long userTaskId) {
        Optional<UserTask> userTask = userTaskRepository.findById(userTaskId);

        return userTask;
    }

    @Override
    @Transactional
    public void completeUserTask(Optional<UserTask> userTaskToComplete) {
        userTaskToComplete.get().setCompleted(!userTaskToComplete.get().getCompleted());
        
        userTaskRepository.saveAndFlush(userTaskToComplete.get());
    }

    @Override
    @Transactional
    public Boolean changePriorityUserTask(Long userTaskId, Integer priority) {
        Optional<UserTask> userTaskToChangePriority = userTaskRepository.findById(userTaskId);

        if (priority >= 1 && priority <= 4) {
            userTaskToChangePriority.get().setPriority(priority);
            return true;
        }

        return false;
    }
}
