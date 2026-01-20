package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import br.com.fiap.clean_arch.presentation.dto.response.MenuItemResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemMapperTest {

    @Test
    void shouldMapMenuItemToResponse() {
        MenuItem menuItem = MenuItem.create(1L, 10L, "Pizza", "Delicious", 
            BigDecimal.valueOf(25.99), true, "http://photo.jpg");
        
        MenuItemResponse response = MenuItemMapper.toResponse(menuItem);
        
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Pizza", response.name());
    }

    @Test
    void shouldMapEntityToDomain() {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(1L);
        entity.setRestaurantId(1L);
        entity.setName("Pizza");
        entity.setDescription("Delicious");
        entity.setPrice(BigDecimal.valueOf(30.0));
        entity.setDeliveryItem(true);
        entity.setPhotoUrl("http://photo.jpg");
        entity.setLastUpdate(ZonedDateTime.now());

        MenuItem menuItem = MenuItemMapper.mapToDomain(entity);

        assertNotNull(menuItem);
        assertEquals(1L, menuItem.getId());
        assertEquals("Pizza", menuItem.getName());
    }
}
