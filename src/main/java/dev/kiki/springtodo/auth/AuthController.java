package dev.kiki.springtodo.auth;

import dev.kiki.springtodo.config.JwtTokenService;
import dev.kiki.springtodo.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenService jwtTokenService;
    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody RegisterUserDTO userDTO) {
        return ResponseEntity.ok(userMapper.toUserResponseDTO(authService.registerUser(userDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        User authenticatedUser = authService.authenticate(userLoginDTO);
        String token = jwtTokenService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(token, jwtTokenService.expirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
