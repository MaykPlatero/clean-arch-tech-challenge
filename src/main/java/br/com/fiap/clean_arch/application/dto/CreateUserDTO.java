package br.com.fiap.clean_arch.application.dto;

public record CreateUserDTO(String name, String email, String userIdentification, String profile, String username,
                            String password) {
}
