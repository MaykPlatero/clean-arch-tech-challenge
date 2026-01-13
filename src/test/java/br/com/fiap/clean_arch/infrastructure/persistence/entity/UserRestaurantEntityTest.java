package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRestaurantEntityTest {

    @Test
    void shouldCreateUserRestaurantEntity() {
        UserRestaurantEntity entity = new UserRestaurantEntity();
        entity.setId(1L);
        entity.setUserId(10L);
        entity.setRestaurantId(20L);
        
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(10L, entity.getUserId());
        assertEquals(20L, entity.getRestaurantId());
    }
}
