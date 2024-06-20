package dev.kiki.springtodo.auth;

import dev.kiki.springtodo.config.JwtTokenService;
import dev.kiki.springtodo.user.LoginResponse;
import dev.kiki.springtodo.user.RegisterUserDTO;
import dev.kiki.springtodo.user.User;
import dev.kiki.springtodo.user.UserLoginDTO;
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

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDTO userDTO) {
        return ResponseEntity.ok(authService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        User authenticatedUser = authService.authenticate(userLoginDTO);
        String token = jwtTokenService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(token, jwtTokenService.expirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
