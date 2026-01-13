package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import br.com.fiap.clean_arch.presentation.dto.UserDTO;
import br.com.fiap.clean_arch.presentation.dto.response.RestaurantResponse;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RestaurantMapper {

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        Set<OpeningHoursDTO> openingHoursDTOSet = 
            OpeningHoursMapper.toResponseDtoSet(restaurant.getOpeningHoursSet());

        Set<UserDTO> userDTOSet = restaurant.getRestaurantOwners().stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toSet());

        return new RestaurantResponse(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getCuisineType(),
            openingHoursDTOSet,
            userDTOSet
        );
    }
}
