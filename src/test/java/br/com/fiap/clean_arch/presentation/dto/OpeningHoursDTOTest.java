package br.com.fiap.clean_arch.presentation.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpeningHoursDTOTest {

    @Test
    void shouldCreateOpeningHoursDTO() {
        String dayOfWeek = "MONDAY";
        String openTime = "10:00";
        String closeTime = "22:00";
        
        OpeningHoursDTO dto = new OpeningHoursDTO(dayOfWeek, openTime, closeTime);
        
        assertNotNull(dto);
        assertEquals(dayOfWeek, dto.dayOfWeek());
        assertEquals(openTime, dto.openTime());
        assertEquals(closeTime, dto.closeTime());
    }
}
