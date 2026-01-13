package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantEntityTest {

    @Test
    void shouldCreateRestaurantEntity() {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(1L);
        entity.setName("Pizza Place");
        entity.setAddress("Rua A, 123");
        entity.setCuisineType("Italian");
        
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Pizza Place", entity.getName());
        assertEquals("Rua A, 123", entity.getAddress());
        assertEquals("Italian", entity.getCuisineType());
    }
}
