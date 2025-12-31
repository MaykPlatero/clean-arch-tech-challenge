package br.com.fiap.clean_arch.presentation.dto;

public record UpdateUserRequest(String name,
                                 String email,
                                 String userIdentification,
                                 String address,
                                 String profile,
                                 String username,
                                 String password) {}

