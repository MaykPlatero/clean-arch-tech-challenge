package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsEntityTest {

    @Test
    void shouldCreateUserCredentialsEntity() {
        UserCredentialsEntity entity = new UserCredentialsEntity();
        entity.setId(1L);
        entity.setUsername("testuser");
        entity.setPassword("password");
        entity.setLastUpdate(ZonedDateTime.now());
        
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("testuser", entity.getUsername());
        assertEquals("password", entity.getPassword());
        assertNotNull(entity.getLastUpdate());
    }
}
