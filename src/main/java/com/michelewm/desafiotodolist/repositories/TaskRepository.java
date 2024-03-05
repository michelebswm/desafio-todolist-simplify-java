package com.michelewm.desafiotodolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelewm.desafiotodolist.domain.Task;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIsDone(boolean done);
}
