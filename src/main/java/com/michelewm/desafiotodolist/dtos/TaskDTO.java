package com.michelewm.desafiotodolist.dtos;

import com.michelewm.desafiotodolist.domain.enums.Priority;
import com.michelewm.desafiotodolist.domain.enums.TaskStatus;

public record TaskDTO(String title, String description, Priority priority, TaskStatus taskStatus, Boolean done ) {

}
