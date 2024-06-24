package com.gestion_taches_back.gestion_taches_back.shell;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import com.gestion_taches_back.gestion_taches_back.dto.UserTaskDto;
import com.gestion_taches_back.gestion_taches_back.entity.UserTask;
import com.gestion_taches_back.gestion_taches_back.services.tasks.TaskService;

@Command(group = "Tasks commands")
public class taskShell {
    private TaskService taskService;

    public taskShell(TaskService taskService) {         
        this.taskService = taskService;
    }

    @Command(command="get-tasks", description="Get tasks by user id")     
    public String getTaskList(@Option(required = true, description = "The user id") Long userId) {
        return taskService.getUserTaskList(userId).toString();
    }

    @Command(command="sort-tasks", description="Get tasks by user id and sort them by date")     
    public String sortTaskList(@Option(required = true, description = "The user id") Long userId) {
        return taskService.sortUserTaskList(userId).toString();
    }

    @Command(command="add-task", description="Add task by user id")     
    public String addTask(
        @Option(required = true, description = "The user id") Long userId,
        @Option(required = true, description = "The name of the task") String name,
        @Option(required = true, description = "The priority of the task") Integer priority,
        @Option(required = true, description = "The description of the task") String desc,
        @Option(required = true, description = "The due date of the task") String dueDate
    ) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setCompleted(false);
        LocalDate localDueDate = LocalDate.parse(dueDate);
        userTask.setDueDate(localDueDate);
        userTask.setPriority(priority);
        userTask.setUserId(userId);
        userTask.setDesc(desc);

        UserTaskDto createdUserTask = taskService.createUserTask(userTask);
        if (createdUserTask == null) {
            return "The task couldn't be added.";
        }

        return "The task has been created.";
    }

    @Command(command="complete-task", description="Complete task by user task id")     
    public String completeUserTask(
        @Option(required = true, description = "The user task id") Long userTaskId
    ) {
        Optional<UserTask> userTask = taskService.getUserTask(userTaskId);
        Boolean isUserTaskPresent = userTask.isPresent();
        if (!isUserTaskPresent) {
            return "The task does not exist.";
        }

        taskService.completeUserTask(userTask);
        
        return "Task completed.";
    }

    @Command(command="change-priority-task", description="Change priority of task by user task id")     
    public String changePriorityOfTask(
        @Option(required = true, description = "The user task id") Long userTaskId,
        @Option(required = true, description = "The priority of the task") Integer priority
    ) {
        if (!taskService.changePriorityUserTask(userTaskId, priority)) {
            return "The priority couldn't be changed.";
        }

        return "The priority has been updated.";
    }

    @Command(command="delete-task", description="Delete task by user task id")     
    public String deleteTask(
        @Option(required = true, description = "The user task id") Long userTaskId
    ) {
        Optional<UserTask> userTask = taskService.getUserTask(userTaskId);
        Boolean isUserTaskPresent = userTask.isPresent();
        if (!isUserTaskPresent) {
            return "The task does not exist.";
        }

        taskService.deleteUserTask(userTaskId);
        
        return "Task deleted.";
    }
}