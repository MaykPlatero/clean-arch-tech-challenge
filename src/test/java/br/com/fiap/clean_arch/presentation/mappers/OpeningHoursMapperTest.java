package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OpeningHoursMapperTest {

    @Test
    void shouldMapToDomainEntity() {
        OpeningHoursDTO dto = new OpeningHoursDTO("MONDAY", "10:00", "22:00");
        
        OpeningHours result = OpeningHoursMapper.toDomainEntity(dto);
        
        assertNotNull(result);
        assertEquals(DayOfWeek.MONDAY, result.getDayOfWeek());
        assertEquals(LocalTime.of(10, 0), result.getOpenTime());
        assertEquals(LocalTime.of(22, 0), result.getCloseTime());
    }

    @Test
    void shouldMapToDomainEntitySet() {
        Set<OpeningHoursDTO> dtos = Set.of(
            new OpeningHoursDTO("MONDAY", "10:00", "22:00"),
            new OpeningHoursDTO("TUESDAY", "11:00", "23:00")
        );
        
        Set<OpeningHours> result = OpeningHoursMapper.toDomainEntitySet(dtos);
        
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldMapFromPersistenceEntity() {
        OpeningHoursEntity entity = new OpeningHoursEntity();
        entity.setId(1L);
        entity.setRestaurantId(1L);
        entity.setDayOfWeek(DayOfWeek.MONDAY);
        entity.setOpenTime(LocalTime.of(10, 0));
        entity.setCloseTime(LocalTime.of(22, 0));
        
        OpeningHours result = OpeningHoursMapper.toDomainEntity(entity);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getRestaurantId());
        assertEquals(DayOfWeek.MONDAY, result.getDayOfWeek());
    }
}
