package com.michelewm.desafiotodolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelewm.desafiotodolist.domain.Task;
import com.michelewm.desafiotodolist.dtos.TaskDTO;
import com.michelewm.desafiotodolist.repositories.TaskRepository;
import com.michelewm.desafiotodolist.services.exceptions.ResourceNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task>  getAllTasks() { return repository.findAll(); }
    
    public Task getTaskById(Long id) { 
        Optional<Task> optionalTask = repository.findById(id);
        return optionalTask.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Task insert(TaskDTO taskData){
        Task newTask = new Task(taskData);
        return repository.save(newTask);
    }
}
