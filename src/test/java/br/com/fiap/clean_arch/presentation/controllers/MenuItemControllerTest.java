package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.*;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuItemController.class)
class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateMenuItemUseCase createMenuItemUseCase;

    @MockBean
    private FindMenuItemUseCase findMenuItemUseCase;

    @MockBean
    private UpdateMenuItemUseCase updateMenuItemUseCase;

    @MockBean
    private DeleteMenuItemUseCase deleteMenuItemUseCase;

    @MockBean
    private FindMenuItemsByRestaurantUseCase findMenuItemsByRestaurantUseCase;

    @Test
    void shouldGetMenuItemById() throws Exception {
        MenuItem menuItem = MenuItem.create(1L, 1L, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        when(findMenuItemUseCase.execute(1L)).thenReturn(menuItem);

        mockMvc.perform(get("/api/menu-items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    void shouldGetMenuItemsByRestaurant() throws Exception {
        MenuItem item1 = MenuItem.create(1L, 1L, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        when(findMenuItemsByRestaurantUseCase.execute(1L)).thenReturn(Arrays.asList(item1));

        mockMvc.perform(get("/api/menu-items/restaurant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"));
    }

    @Test
    void shouldDeleteMenuItem() throws Exception {
        mockMvc.perform(delete("/api/menu-items/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldCreateMenuItem() throws Exception {
        MenuItem menuItem = MenuItem.create(1L, 1L, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        when(createMenuItemUseCase.execute(anyLong(), anyString(), anyString(), 
            any(BigDecimal.class), anyBoolean(), anyString())).thenReturn(menuItem);

        String json = "{\"restaurantId\":1,\"name\":\"Pizza\",\"description\":\"Delicious\",\"price\":30.0,\"deliveryItem\":true,\"photoUrl\":\"http://photo.jpg\"}";

        mockMvc.perform(post("/api/menu-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Pizza"));
    }
}
