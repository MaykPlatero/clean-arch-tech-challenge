package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.CreateUserUseCase;
import br.com.fiap.clean_arch.application.usecases.FindUserUseCase;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.presentation.dto.CreateUserRequest;
import br.com.fiap.clean_arch.presentation.dto.UserResponse;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Users management API")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, FindUserUseCase getUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = getUserUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = createUserUseCase.execute(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = findUserUseCase.execute(id);
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }
}
