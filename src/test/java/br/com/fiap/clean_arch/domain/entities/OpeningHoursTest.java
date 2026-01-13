package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class OpeningHoursTest {

    @Test
    void shouldCreateOpeningHoursWithAllFields() {
        Long id = 1L;
        Long restaurantId = 10L;
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        LocalTime openTime = LocalTime.of(10, 0);
        LocalTime closeTime = LocalTime.of(22, 0);
        
        OpeningHours openingHours = new OpeningHours(id, restaurantId, dayOfWeek, openTime, closeTime);
        
        assertNotNull(openingHours);
        assertEquals(id, openingHours.getId());
        assertEquals(restaurantId, openingHours.getRestaurantId());
        assertEquals(dayOfWeek, openingHours.getDayOfWeek());
        assertEquals(openTime, openingHours.getOpenTime());
        assertEquals(closeTime, openingHours.getCloseTime());
    }

    @Test
    void shouldCreateOpeningHoursWithoutId() {
        Long restaurantId = 10L;
        DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
        LocalTime openTime = LocalTime.of(18, 0);
        LocalTime closeTime = LocalTime.of(23, 30);
        
        OpeningHours openingHours = new OpeningHours(null, restaurantId, dayOfWeek, openTime, closeTime);
        
        assertNotNull(openingHours);
        assertNull(openingHours.getId());
        assertEquals(restaurantId, openingHours.getRestaurantId());
        assertEquals(dayOfWeek, openingHours.getDayOfWeek());
        assertEquals(openTime, openingHours.getOpenTime());
        assertEquals(closeTime, openingHours.getCloseTime());
    }
}
