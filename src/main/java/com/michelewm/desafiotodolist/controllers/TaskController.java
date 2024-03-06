package com.michelewm.desafiotodolist.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.michelewm.desafiotodolist.controllers.util.URL;
import com.michelewm.desafiotodolist.domain.Task;
import com.michelewm.desafiotodolist.domain.enums.Priority;
import com.michelewm.desafiotodolist.dtos.TaskDTO;
import com.michelewm.desafiotodolist.services.TaskService;
import com.michelewm.desafiotodolist.services.exceptions.ValidationTaskException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = service.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    public ResponseEntity<Task> insert(@RequestBody @Valid TaskDTO taskData) {
        Task newTask = service.insert(taskData);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTask.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody @Valid TaskDTO taskData) {
        Task updatedTask = service.update(id, taskData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> findByIsDone(@RequestParam(name = "done") boolean done) {
        List<Task> tasks = service.findByIsDone(done);
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Task>> findByTitleContainingIgnoreCase(
            @RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Task> tasks = service.findByTitleContainingIgnoreCase(text);
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/priority")
    public ResponseEntity<List<Task>> findByPriority(@RequestParam(value = "priority") String priority) {
        try {
            priority = URL.decodeParam(priority);
            Priority priorityEnum = Priority.valueOf(priority.toUpperCase());
            List<Task> tasks = service.findByPriority(priorityEnum);
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (IllegalArgumentException ex) {
            throw new ValidationTaskException("Invalid priority" + ex.getMessage());
        }
    }

}
