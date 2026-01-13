package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpeningHoursEntityTest {

    @Test
    void shouldCreateOpeningHoursEntity() {
        OpeningHoursEntity entity = new OpeningHoursEntity();
        entity.setId(1L);
        entity.setRestaurantId(10L);
        entity.setDayOfWeek(DayOfWeek.MONDAY);
        entity.setOpenTime(LocalTime.of(10, 0));
        entity.setCloseTime(LocalTime.of(22, 0));
        
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(10L, entity.getRestaurantId());
        assertEquals(DayOfWeek.MONDAY, entity.getDayOfWeek());
        assertEquals(LocalTime.of(10, 0), entity.getOpenTime());
        assertEquals(LocalTime.of(22, 0), entity.getCloseTime());
    }
}
