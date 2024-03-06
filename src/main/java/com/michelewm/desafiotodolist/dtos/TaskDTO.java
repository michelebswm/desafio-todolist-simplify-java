package com.michelewm.desafiotodolist.dtos;

import jakarta.validation.constraints.NotBlank;

public record TaskDTO(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String priority,
        @NotBlank String taskStatus,
        Boolean done) {

}
