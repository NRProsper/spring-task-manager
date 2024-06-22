package dev.kiki.springtodo.task;

public record TaskDTO(
        Long id,
        String title,
        String description,
        Status status,
        Long userId
) {
}
