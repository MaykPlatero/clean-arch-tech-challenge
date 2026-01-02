package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.presentation.dto.RestaurantResponse;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RestaurantMapper {

    public static Restaurant toDomainEntity(RestaurantEntity restaurantEntity,
                                            Set<UserEntity> userSet,
                                            List<OpeningHoursEntity> openingHoursEntityList) {
        List<OpeningHours> openingHoursList = OpeningHoursMapper.toDomainEntityList(openingHoursEntityList);

        Set<User> usersSet = userSet.stream()
            .map(UserMapper::toDomainEntity)
            .collect(java.util.stream.Collectors.toSet());

        return Restaurant.create(
            restaurantEntity.getName(),
            restaurantEntity.getAddress(),
            restaurantEntity.getCuisineType(),
            usersSet,
            openingHoursList
        );
    }

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        return new RestaurantResponse(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getCuisineType()
        );
    }
}
