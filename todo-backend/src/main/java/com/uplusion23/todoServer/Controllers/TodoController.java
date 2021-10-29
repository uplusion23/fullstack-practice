package com.uplusion23.todoServer.Controllers;

import com.uplusion23.todoServer.Models.Todo;
import com.uplusion23.todoServer.Repositories.TodoRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TodoController {
    TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    public Iterable<Todo> getUserItems(@RequestParam Long userId) {
        return this.repository.findAllByUserId(userId);
    }

    @GetMapping("/items/all")
    public Iterable<Todo> getAllItems() {
        return this.repository.findAll();
    }

    @PostMapping("/items")
    public Object createItem(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        try {
            this.repository.save(todo);
            return Map.of("response","success", "data", todo);
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }

    @PatchMapping("/items/{id}")
    public Object updateItem(@PathVariable Long id, @RequestBody Todo todo) {
        try {
            Todo todoItem = this.repository.findById(id).get();
            todoItem.setCompleted(todo.isCompleted());
            todoItem.setContent(todo.getContent());
            todoItem.setUserId(todo.getUserId());
            this.repository.save(todoItem);
            return Map.of("response", "success", "data", todoItem);
        } catch (Exception e) {
            return Map.of("error", "Item not found");
        }
    }

    @DeleteMapping("/items/{id}")
    public Object deleteItem(@PathVariable Long id, @RequestBody Long userId) {
        try {
            if (this.repository.findById(id).get().getUserId() == userId) {
                this.repository.deleteById(id);
                return Map.of("response", "success", "data", "Deleted item with id " + id);
            } else {
                return Map.of("error", "You are not authorized to delete this item");
            }
        } catch (Exception e) {
            return Map.of("error", "Item not found");
        }
    }
}