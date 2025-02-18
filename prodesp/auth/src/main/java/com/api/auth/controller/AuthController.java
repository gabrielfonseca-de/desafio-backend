package com.api.auth.controller;

import com.api.auth.dto.LoginRequest;
import com.api.auth.dto.LoginResponse;
import com.api.auth.model.User;
import com.api.auth.service.AuthService;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import com.api.auth.service.AuthService;
import com.api.auth.service.RabbitMQService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final RabbitMQService rabbitMQService;

    public AuthController(AuthService authService, RabbitMQService rabbitMQService) {
        this.authService = authService;
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return new LoginResponse("TokenJWT-Aqui"); // Simulação de token JWT
    }

    @PostMapping("/task")
    public String createTask(@RequestBody Task task) {
        rabbitMQService.sendMessage("taskExchange", "task.create", task);
        return "Tarefa criada e mensagem enviada!";
    }
}
