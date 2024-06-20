package dev.kiki.springtodo.user;

public record RegisterUserDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
