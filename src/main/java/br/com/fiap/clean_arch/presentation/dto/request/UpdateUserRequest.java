package br.com.fiap.clean_arch.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


public record UpdateUserRequest(

                                @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras")
                                String name,
                                @Email
                                 String email,

                                String userIdentification,
                                String address,
                                String profile,
                                String username,
                                String password) {}

