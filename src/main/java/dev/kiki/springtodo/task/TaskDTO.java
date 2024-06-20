package dev.kiki.springtodo.task;

import jakarta.validation.constraints.NotBlank;

public record TaskDTO(
        @NotBlank(message = "The task title is Mandatory!")
        String title,
        String description
) {
}
