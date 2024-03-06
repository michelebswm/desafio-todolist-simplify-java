package com.michelewm.desafiotodolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.michelewm.desafiotodolist.domain.Task;
import com.michelewm.desafiotodolist.domain.enums.Priority;
import com.michelewm.desafiotodolist.domain.enums.TaskStatus;
import com.michelewm.desafiotodolist.dtos.TaskDTO;
import com.michelewm.desafiotodolist.repositories.TaskRepository;
import com.michelewm.desafiotodolist.services.exceptions.ResourceNotFoundException;
import com.michelewm.desafiotodolist.services.exceptions.ValidationTaskException;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        Sort sort = Sort.by("priority").descending().and(Sort.by("title").ascending());
        return repository.findAll(sort);
    }

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        return optionalTask.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Task insert(TaskDTO taskData) {
        validateTasks(TaskStatus.valueOf(taskData.taskStatus()), taskData.done());
        Task newTask = new Task(taskData);
        return repository.save(newTask);
    }

    public Task update(Long id, TaskDTO taskData) {
        Task task = getTaskById(id);

        validateTasks(TaskStatus.valueOf(taskData.taskStatus()), taskData.done());
        if (!task.getTitle().isEmpty())
            task.setTitle(taskData.title());
        if (!task.getDescription().isEmpty())
            task.setDescription(taskData.description());
        if (!(task.getPriority() == null))
            task.setPriority(Priority.valueOf(taskData.priority()));
        if (!(task.getTaskStatus() == null))
            task.setTaskStatus(TaskStatus.valueOf(taskData.taskStatus()));
        task.setDone(taskData.done());
        return repository.save(task);
    }

    public void delete(Long id) {
        Task task = getTaskById(id);
        repository.delete(task);
    }

    public List<Task> findByIsDone(boolean done) {
        return repository.findByIsDone(done);
    }

    public List<Task> findByTitleContainingIgnoreCase(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<Task> findByPriority(Priority priority) {
        return repository.findByPriority(priority);
    }

    public void validateTasks(TaskStatus taskStatus, Boolean done) {
        if (taskStatus.getCode() == 2 && done == false) {
            throw new ValidationTaskException("Status DONE precisa ser marcado como concluído");
        }
        if (done == true && taskStatus.getCode() != 2) {
            throw new ValidationTaskException("O status CONCLUÍDO só pode ser usado para tarefas finalizadas");
        }
    }
}
