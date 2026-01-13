package br.com.fiap.clean_arch.presentation.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserRequestTest {

    @Test
    void shouldCreateUserRequest() {
        String name = "John Doe";
        String email = "john@test.com";
        String userIdentification = "123456789";
        String address = "Rua A, 123";
        String profile = "client";
        String username = "john";
        String password = "password";
        
        CreateUserRequest request = new CreateUserRequest(name, email, userIdentification, address, profile, username, password);
        
        assertNotNull(request);
        assertEquals(name, request.name());
        assertEquals(email, request.email());
        assertEquals(userIdentification, request.userIdentification());
        assertEquals(address, request.address());
        assertEquals(profile, request.profile());
        assertEquals(username, request.username());
        assertEquals(password, request.password());
    }
}
