package br.com.fiap.clean_arch.presentation.dto;

public record CreateUserRequest(String name,
                                String email,
                                String userIdentification,
                                String address,
                                String profile,
                                String username,
                                String password) {}
