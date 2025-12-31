package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.UserUseCase;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.presentation.dto.CreateRestaurantRequest;
import br.com.fiap.clean_arch.presentation.dto.CreateUserRequest;
import br.com.fiap.clean_arch.presentation.dto.UserResponse;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Users management API")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = userUseCase.execute(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }

}
