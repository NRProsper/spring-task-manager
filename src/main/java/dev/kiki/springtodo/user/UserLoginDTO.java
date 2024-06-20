package dev.kiki.springtodo.user;

public record UserLoginDTO(
        String email,
        String password
) {
}
