package com.michelewm.desafiotodolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelewm.desafiotodolist.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
