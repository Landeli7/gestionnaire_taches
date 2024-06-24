package com.gestion_taches_back.gestion_taches_back.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import com.gestion_taches_back.gestion_taches_back.controller.TaskController;
import com.gestion_taches_back.gestion_taches_back.entity.User;
import com.gestion_taches_back.gestion_taches_back.entity.UserTask;

// @RunWith(SpringJUnit5ClassRunner.class)
@SpringBootTest
public class TaskControllerTest {

	// @Autowired
	// private TaskController taskController;

	// private User testUser = new User();

	// private UserTask testUserTask = new UserTask();

	// public TaskControllerTest() {
	// 	testUser.setName("testName");
	// 	testUser.setEmail("test@email.com");
	// 	testUser.setPass("testPass");

	// 	testUserTask.setName("testName");
	// 	testUserTask.setDesc("Test description");
	// 	LocalDate dueDate = LocalDate.parse("2024-12-31");
	// 	testUserTask.setDueDate(dueDate);
	// 	testUserTask.setCompleted(false);
	// 	testUserTask.setUserId(testUser.getId());
	// 	testUserTask.setPriority(2);
	// }

	// // TASKS

	// @Test
	// void taskControllerLoads() throws Exception {
	// 	assertThat(taskController).isNotNull();
	// }

	// @Test
	// void createTask() throws Exception {
	// 	ResponseEntity<String> res = taskController.addUserTask(testUserTask, null);

	// 	assertThat(res.getBody()).isEqualTo("Tâche créée.");
	// }

	// @Test
	// void getTaskList() throws Exception {
	// 	ResponseEntity<List<UserTask>> res = taskController.getUserTaskList(testUser.getId());

	// 	assertThat(res.getBody()).isNotEmpty();
	// }

	// @Test
	// void sortTaskList() throws Exception {
	// 	ResponseEntity<List<UserTask>> res = taskController.sortUserTaskList(testUser.getId());

	// 	assertThat(res.getBody()).isNotEmpty();
	// }

	// @Test
	// void completeTask() throws Exception {
	// 	ResponseEntity<String> res = taskController.completeUserTask(testUser.getId());

	// 	assertThat(res.getBody()).isEqualTo("Tâche complétée.");
	// }

	// @Test
	// void changePriorityTask() throws Exception {

	// 	Integer priority = 4;

	// 	ResponseEntity<String> res = taskController.changePriorityUserTask(testUser.getId(), priority);

	// 	assertThat(res.getBody()).isEqualTo("Niveau de priorité passé en " + priority + ".");
	// }

	// @Test
	// void deleteTask() throws Exception {

	// 	ResponseEntity<String> res = taskController.deleteUserTask(testUser.getId());

	// 	assertThat(res.getBody()).isEqualTo("Tâche supprimée.");
	// }

}
