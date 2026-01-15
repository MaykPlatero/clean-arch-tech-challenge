package br.com.fiap.clean_arch.presentation.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(
        @NotBlank(message = "Name are required")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras")
        String name,
        @NotBlank(message = "Email are required")
        @Email(message = "Email must be valid")
        String email,
        @NotNull(message = "User IDs are required")
        String userIdentification,
        @NotBlank(message = "Address is required")
        String address,
        @NotBlank(message = "profile are required")
        String profile,
        @NotBlank(message = "nick are required")
        String username,
        @NotBlank(message = "senha are required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password) {}
