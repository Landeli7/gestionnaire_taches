package com.gestion_taches_back.gestion_taches_back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gestion_taches_back.gestion_taches_back.dto.UserTaskDto;
import com.gestion_taches_back.gestion_taches_back.entity.UserTask;
import com.gestion_taches_back.gestion_taches_back.services.tasks.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private HttpHeaders responseHeaders = new HttpHeaders();

    private String formatResp(String resp) {
        return "[\"" + resp + "\"]";
    }

    private void setHeadersContentType(MediaType mediaType) {
        responseHeaders.setContentType(mediaType);
    }

    @GetMapping("/getList/{userId}")
    public ResponseEntity<List<UserTask>> getUserTaskList(@PathVariable Long userId) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        List<UserTask> userTaskList = taskService.getUserTaskList(userId);
        return new ResponseEntity<List<UserTask>>(userTaskList, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/sortList/{userId}")
    public ResponseEntity<List<UserTask>> sortUserTaskList(@PathVariable Long userId) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        List<UserTask> userTaskList = taskService.sortUserTaskList(userId);
        return new ResponseEntity<List<UserTask>>(userTaskList, responseHeaders, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addUserTask(@RequestBody @Valid UserTask userTask, BindingResult bindingResult) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        if (bindingResult.hasErrors()) {
            String errorMsg = "Un ou plusieurs champ(s) ne sont pas remplis correctement.";
            // Validation errors
            if (userTask.getName().length() < 3 || userTask.getName().length() > 20) {
                errorMsg = "La longueur du champ 'Nom' doit être comprise entre 3 et 20 caractères.";
            } else if (userTask.getDesc().length() < 5 || userTask.getDesc().length() > 50) {
                errorMsg = "La longueur du champ 'Description' doit être comprise entre 5 et 50 caractères.";
            } else if (userTask.getDueDate() == null) {
                errorMsg = "Le champ 'Date d'échéance' n'a pas été rempli correctement.";
            }

            return new ResponseEntity<String>(formatResp(errorMsg), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        LocalDate dueDateOfTask = userTask.getDueDate();
        LocalDate currentDate = LocalDate.now();

        if (dueDateOfTask.isBefore(currentDate)) {
            return new ResponseEntity<String>(formatResp("Impossible de définir une date d'échéance avant aujourd'hui."), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        UserTaskDto createdUserTask = taskService.createUserTask(userTask);
        if (createdUserTask == null) {
            return new ResponseEntity<String>(formatResp("Erreur lors de la création de la tâche."), responseHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(formatResp("Tâche créée."), responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userTaskId}")
    public ResponseEntity<String> deleteUserTask(@PathVariable Long userTaskId) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        Optional<UserTask> userTask = taskService.getUserTask(userTaskId);
        Boolean isUserTaskPresent = userTask.isPresent();
        if (!isUserTaskPresent) {
            return new ResponseEntity<String>(formatResp("Cette tâche n'existe pas."), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        taskService.deleteUserTask(userTaskId);

        return new ResponseEntity<String>(formatResp("Tâche supprimée."), responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/complete/{userTaskId}")
    public ResponseEntity<String> completeUserTask(@PathVariable Long userTaskId) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        Optional<UserTask> userTask = taskService.getUserTask(userTaskId);
        Boolean isUserTaskPresent = userTask.isPresent();
        if (!isUserTaskPresent) {
            return new ResponseEntity<String>(formatResp("Cette tâche n'existe pas."), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        taskService.completeUserTask(userTask);

        return new ResponseEntity<String>(formatResp("Tâche complétée."), responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/changePriority/{userTaskId}")
    public ResponseEntity<String> completeUserTask(@PathVariable Long userTaskId, @RequestBody Integer userTaskPriority) {
        
        setHeadersContentType(MediaType.APPLICATION_JSON);

        Optional<UserTask> userTask = taskService.getUserTask(userTaskId);
        Boolean isUserTaskPresent = userTask.isPresent();
        if (!isUserTaskPresent) {
            return new ResponseEntity<String>(formatResp("Cette tâche n'existe pas."), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        Boolean isUserTaskPriorityChanged = taskService.changePriorityUserTask(userTaskId, userTaskPriority);
        if (!isUserTaskPriorityChanged) {
            return new ResponseEntity<String>(formatResp("Erreur lors du changement de niveau de priorité."), responseHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(formatResp("Niveau de priorité passé en " + userTaskPriority + "."), responseHeaders, HttpStatus.OK);
    }
}
