package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserCredentialsEntity;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsMapperTest {

    @Test
    void shouldMapToDomainEntity() {
        UserCredentialsEntity entity = new UserCredentialsEntity();
        entity.setId(1L);
        entity.setUsername("user1");
        entity.setPassword("pass123");
        entity.setLastUpdate(ZonedDateTime.now());

        UserCredentials credentials = UserCredentialsMapper.toDomainEntity(entity);

        assertNotNull(credentials);
        assertEquals(1L, credentials.getId());
        assertEquals("user1", credentials.getUsername());
    }

    @Test
    void shouldMapToPersistenceEntity() {
        UserCredentials credentials = UserCredentials.create(1L, "user1", "pass123", ZonedDateTime.now());

        UserCredentialsEntity entity = UserCredentialsMapper.toPersistenceEntity(credentials);

        assertNotNull(entity);
        assertEquals("user1", entity.getUsername());
        assertEquals("pass123", entity.getPassword());
    }
}
