package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;

import java.util.ArrayList;
import java.util.List;

public class OpeningHoursMapper {

    public static OpeningHours toDomainEntity(OpeningHoursEntity openingHoursEntity) {
        return new OpeningHours(
            openingHoursEntity.getDayOfWeek(),
            openingHoursEntity.getOpenTime(),
            openingHoursEntity.getCloseTime()
        );
    }

    public static List<OpeningHours> toDomainEntityList(List<OpeningHoursEntity> openingHoursEntity) {
        List<OpeningHours> openingHoursList = new ArrayList<>();

        for (OpeningHoursEntity entity : openingHoursEntity) {
            openingHoursList.add(toDomainEntity(entity));
        }

        return openingHoursList;
    }
}
