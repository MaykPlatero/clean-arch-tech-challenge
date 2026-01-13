package br.com.fiap.clean_arch.presentation.dto.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateMenuItemRequestTest {

    @Test
    void shouldCreateMenuItemRequest() {
        Long restaurantId = 1L;
        String name = "Pizza";
        String description = "Delicious pizza";
        BigDecimal price = BigDecimal.valueOf(25.99);
        Boolean deliveryItem = true;
        String photoUrl = "http://photo.jpg";
        
        CreateMenuItemRequest request = new CreateMenuItemRequest(restaurantId, name, description, price, deliveryItem, photoUrl);
        
        assertNotNull(request);
        assertEquals(restaurantId, request.restaurantId());
        assertEquals(name, request.name());
        assertEquals(description, request.description());
        assertEquals(price, request.price());
        assertEquals(deliveryItem, request.deliveryItem());
        assertEquals(photoUrl, request.photoUrl());
    }
}
