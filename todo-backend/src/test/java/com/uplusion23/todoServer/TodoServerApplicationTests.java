package com.uplusion23.todoServer;

import org.junit.jupiter.api.Test;
import com.uplusion23.todoServer.Models.Todo;
import com.uplusion23.todoServer.Repositories.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoServerApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TodoRepository todoRepository;

	@Test
	void canGetAllItems() throws Exception {

		mockMvc.perform(get("/api/items")
		        .contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray());
	}

	@Test
	void canPostNewItem() throws Exception {
		Todo todo = createTodo("Test the application", false);

		mockMvc.perform(post("/api/items")
						.content(convertObjectToJsonBytes(todo))
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.content").value("Test the application"));
	}

	@Test
	void canUpdateTodoItem() throws Exception {
		Todo todo = createTodo("Test Update Object", false);

		mockMvc.perform(post("/api/items")
				.content(convertObjectToJsonBytes(todo))
				.contentType("application/json"));

		todo.setCompleted(true);

		mockMvc.perform(patch("/api/items/" + todo.getId())
						.content(convertObjectToJsonBytes(todo))
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.completed").value(true));
	}

	@Test
	void canNotUpdateInvalidItem() throws Exception {
		Todo todo = createTodo("Test Update Object", false);

		mockMvc.perform(patch("/api/items/255")
						.content(convertObjectToJsonBytes(todo))
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.error").value("Item not found"));
	}

	@Test
	void canDeleteTodoItem() throws Exception {
		Todo todo = createTodo("Test Delete Object", false);

		mockMvc.perform(post("/api/items")
				.content(convertObjectToJsonBytes(todo))
				.contentType("application/json"));

		todo.setCompleted(true);

		mockMvc.perform(delete("/api/items/" + todo.getId())
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.response").value("success"))
				.andExpect(jsonPath("$.data").value("Deleted item with id " + todo.getId()));
	}

	@Test
	void canNotDeleteInvalidItem() throws Exception {
		mockMvc.perform(delete("/api/items/255")
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.error").value("Item not found"));
	}

	private byte[] convertObjectToJsonBytes(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	private Todo createTodo(String content, boolean completed) {
		Todo todo = new Todo();
		todo.setContent(content);
		todo.setCompleted(completed);
		this.todoRepository.save(todo);
		return todo;
	}

}
