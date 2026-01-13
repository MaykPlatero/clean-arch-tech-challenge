package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.presentation.dto.response.UserResponse;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void shouldMapUserToResponse() {
        UserCredentials credentials = UserCredentials.create(1L, "testuser", "password", ZonedDateTime.now());
        User user = User.create(1L, "John Doe", "123456789", "john@test.com", 
            "Rua A, 123", credentials, EProfile.client, ZonedDateTime.now());
        
        UserResponse response = UserMapper.toResponse(user);
        
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("John Doe", response.name());
        assertEquals("123456789", response.userIdentification());
        assertEquals("john@test.com", response.email());
        assertEquals("Rua A, 123", response.address());
        assertEquals("client", response.profile().toString());
    }
}
