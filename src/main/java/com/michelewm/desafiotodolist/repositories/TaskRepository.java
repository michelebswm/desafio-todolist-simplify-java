package com.michelewm.desafiotodolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelewm.desafiotodolist.domain.Task;
import java.util.List;
import com.michelewm.desafiotodolist.domain.enums.Priority;



public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIsDone(boolean done);
    List<Task> findByTitleContainingIgnoreCase(String title);
    List<Task> findByPriority(Priority priority);
}
