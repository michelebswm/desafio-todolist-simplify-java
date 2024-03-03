package com.michelewm.desafiotodolist.domain;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.michelewm.desafiotodolist.domain.enums.Priority;
import com.michelewm.desafiotodolist.domain.enums.TaskStatus;
import com.michelewm.desafiotodolist.dtos.TaskDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(nullable =  false, length=250)
    private String title;
    private String description;
    
    @Column(columnDefinition="TINYINT NOT NULL DEFAULT 0", name ="is_done")
    private boolean isDone;

    @Enumerated(EnumType.ORDINAL)
    private Priority  priority;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime  creationDate = LocalDateTime.now();

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus taskStatus;

    public Task(TaskDTO taskDto){
        this.title = taskDto.title();
        this.description = taskDto.description();
        this.priority = taskDto.priority();
        this.taskStatus = taskDto.taskStatus();
    }
}   
