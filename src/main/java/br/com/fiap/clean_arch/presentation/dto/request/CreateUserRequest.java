package br.com.fiap.clean_arch.presentation.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Name é obrigatorio")
        String name,
        @NotBlank(message = "Email é Obrigatorio")
        @Email(message = "Email deve ser válido")
        String email,
        @NotNull(message = "User IDs are required")
        String userIdentification,
        @NotBlank(message = "Address is required")
        String address,
        String profile,
        @NotBlank(message = "nick name é obrigatorio")
        String username,
        @NotBlank(message = "campo senha é obrigatorio")
        @Size(min = 6, message = "Senha deve ter no minimo 6 caracteres")
        String password) {}
