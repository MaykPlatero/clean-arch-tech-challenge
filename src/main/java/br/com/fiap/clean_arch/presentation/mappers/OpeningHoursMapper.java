package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class OpeningHoursMapper {

    public static OpeningHours toDomainEntity(OpeningHoursEntity openingHoursEntity) {
        return new OpeningHours(
            openingHoursEntity.getDayOfWeek(),
            openingHoursEntity.getOpenTime(),
            openingHoursEntity.getCloseTime()
        );
    }

    public static OpeningHours toDomainEntity(OpeningHoursDTO request) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(request.dayOfWeek().toUpperCase());
        LocalTime openTime = LocalTime.parse(request.openTime());
        LocalTime closeTime = LocalTime.parse(request.closeTime());
        return new OpeningHours(dayOfWeek, openTime, closeTime);
    }

    public static Set<OpeningHours> toDomainEntitySet(Set<OpeningHoursDTO> requests) {
        Set<OpeningHours> openingHoursSet = new java.util.HashSet<>();
        if (requests != null) {
            for (OpeningHoursDTO req : requests) {
                openingHoursSet.add(toDomainEntity(req));
            }
        }
        return openingHoursSet;
    }

    public static Set<OpeningHoursDTO> toResponseDtoSet(Set<OpeningHours> openingHoursSet) {
        Set<OpeningHoursDTO> openingHoursDTOSet = new HashSet<>();

        for (OpeningHours openingHours : openingHoursSet) {
            OpeningHoursDTO dto = new OpeningHoursDTO(
                openingHours.getDayOfWeek().name(),
                openingHours.getOpenTime().toString(),
                openingHours.getCloseTime().toString()
            );
            openingHoursDTOSet.add(dto);
        }

        return openingHoursDTOSet;
    }

    public static Set<OpeningHours> toDomainEntityList(Set<OpeningHoursEntity> openingHoursEntity) {
        Set<OpeningHours> openingHoursList = new HashSet<>();

        for (OpeningHoursEntity entity : openingHoursEntity) {
            openingHoursList.add(toDomainEntity(entity));
        }

        return openingHoursList;
    }



    public static Set<OpeningHoursEntity> toPersistenceEntitySet(Set<OpeningHours> openingHoursSet) {
        Set<OpeningHoursEntity> openingHoursEntities = new HashSet<>();

        for (OpeningHours openingHours : openingHoursSet) {
            OpeningHoursEntity entity = new OpeningHoursEntity();
            entity.setDayOfWeek(openingHours.getDayOfWeek());
            entity.setOpenTime(openingHours.getOpenTime());
            entity.setCloseTime(openingHours.getCloseTime());
            openingHoursEntities.add(entity);
        }

        return openingHoursEntities;
    }
}
