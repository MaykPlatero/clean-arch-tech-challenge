package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemEntityTest {

    @Test
    void shouldCreateMenuItemEntity() {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(1L);
        entity.setName("Pizza");
        entity.setDescription("Delicious pizza");
        entity.setPrice(BigDecimal.valueOf(25.99));
        entity.setDeliveryItem(true);
        entity.setPhotoUrl("http://photo.jpg");
        entity.setRestaurantId(10L);
        entity.setLastUpdate(ZonedDateTime.now());
        
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Pizza", entity.getName());
        assertEquals("Delicious pizza", entity.getDescription());
        assertEquals(BigDecimal.valueOf(25.99), entity.getPrice());
        assertTrue(entity.isDeliveryItem());
        assertEquals("http://photo.jpg", entity.getPhotoUrl());
        assertEquals(10L, entity.getRestaurantId());
        assertNotNull(entity.getLastUpdate());
    }
}
