package com.michelewm.desafiotodolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.michelewm.desafiotodolist.domain.Task;
import com.michelewm.desafiotodolist.domain.enums.Priority;
import com.michelewm.desafiotodolist.domain.enums.TaskStatus;
import com.michelewm.desafiotodolist.dtos.TaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DesafioTodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTaskSuccess() {
		var taskDto = new TaskDTO("Todo 1", "desc todo 1", "HIGH", "DOING", false);
		var task = new Task(taskDto);

		webTestClient
				.post()
				.uri("/api/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isMap()
				.jsonPath("$.length()").isEqualTo(7)
				.jsonPath("$.title").isEqualTo(task.getTitle())
				.jsonPath("$.description").isEqualTo(task.getDescription())
				.jsonPath("$.taskStatus").isEqualTo(task.getTaskStatus().toString())
				.jsonPath("$.priority").isEqualTo(task.getPriority().toString())
				.jsonPath("$.done").isEqualTo(task.isDone());
	}

	@Test
	void testCreateTaskFailure() {
		var task = new Task(new TaskDTO("", "", "LOW", "TODO", false));
		webTestClient
				.post()
				.uri("/api/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isBadRequest();
	}

}
