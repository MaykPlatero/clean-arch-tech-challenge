package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import br.com.fiap.clean_arch.domain.entities.EProfile;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void shouldCreateUserEntity() {
        UserCredentialsEntity credentials = new UserCredentialsEntity();
        credentials.setId(1L);
        credentials.setUsername("testuser");
        credentials.setPassword("password");
        
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("John Doe");
        entity.setUserIdentification("123456789");
        entity.setEmail("john@test.com");
        entity.setAddress("Rua A, 123");
        entity.setUserCredentials(credentials);
        entity.setProfile(EProfile.client);
        entity.setLastUpdate(ZonedDateTime.now());
        
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("John Doe", entity.getName());
        assertEquals("123456789", entity.getUserIdentification());
        assertEquals("john@test.com", entity.getEmail());
        assertEquals("Rua A, 123", entity.getAddress());
        assertEquals(EProfile.client, entity.getProfile());
        assertNotNull(entity.getUserCredentials());
        assertNotNull(entity.getLastUpdate());
    }
}
