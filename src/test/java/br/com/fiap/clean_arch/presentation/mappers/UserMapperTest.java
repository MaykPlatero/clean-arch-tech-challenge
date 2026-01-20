package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.presentation.dto.UserDTO;
import br.com.fiap.clean_arch.presentation.dto.response.UserResponse;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void shouldMapUserToResponse() {
        UserCredentials credentials = UserCredentials.create(1L, "testuser", "password123", ZonedDateTime.now());
        User user = User.create(1L, "John Doe", "123456789", "john@test.com", 
            "Rua A, 123", credentials, EProfile.CLIENT, ZonedDateTime.now());

        UserResponse response = UserMapper.toResponse(user);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("John Doe", response.name());
    }

    @Test
    void shouldMapUserToDTO() {
        UserCredentials credentials = UserCredentials.create(1L, "testuser", "password123", ZonedDateTime.now());
        User user = User.create(1L, "John Doe", "123456789", "john@test.com", 
            "Rua A, 123", credentials, EProfile.CLIENT, ZonedDateTime.now());

        UserDTO dto = UserMapper.toDTO(user);

        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertEquals("John Doe", dto.name());
        assertEquals("john@test.com", dto.email());
    }

    @Test
    void shouldMapUserToPersistenceEntity() {
        UserCredentials credentials = UserCredentials.create(1L, "testuser", "password123", ZonedDateTime.now());
        User user = User.create(1L, "John Doe", "123456789", "john@test.com", 
            "Rua A, 123", credentials, EProfile.CLIENT, ZonedDateTime.now());

        br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity entity = 
            UserMapper.toPersistenceEntity(user);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("John Doe", entity.getName());
        assertEquals("john@test.com", entity.getEmail());
    }
}
