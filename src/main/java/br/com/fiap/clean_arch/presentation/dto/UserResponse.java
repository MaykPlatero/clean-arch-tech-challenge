package br.com.fiap.clean_arch.presentation.dto;

public record UserResponse(Long id,
                           String name,
                           String email,
                           String userIdentification,
                           String address,
                           String profile) {}
