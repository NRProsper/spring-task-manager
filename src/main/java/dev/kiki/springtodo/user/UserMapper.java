package dev.kiki.springtodo.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

}
