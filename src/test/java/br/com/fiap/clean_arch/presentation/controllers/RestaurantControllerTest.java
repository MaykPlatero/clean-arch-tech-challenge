package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.*;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateRestaurantUseCase createRestaurantUseCase;

    @MockBean
    private FindRestaurantUseCase findRestaurantUseCase;

    @MockBean
    private UpdateRestaurantUseCase updateRestaurantUseCase;

    @Test
    void shouldGetRestaurantById() throws Exception {
        Set<OpeningHours> hours = new HashSet<>();
        hours.add(new OpeningHours(1L, 1L, DayOfWeek.MONDAY, LocalTime.of(18, 0), LocalTime.of(23, 0)));
        
        Restaurant restaurant = Restaurant.create(1L, "Pizzaria", "Address", "Italian", new HashSet<>(), hours);

        when(findRestaurantUseCase.execute(1L)).thenReturn(restaurant);

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Pizzaria"));
    }
}
