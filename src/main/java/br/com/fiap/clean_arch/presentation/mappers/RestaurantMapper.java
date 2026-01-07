package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import br.com.fiap.clean_arch.presentation.dto.UserDTO;
import br.com.fiap.clean_arch.presentation.dto.response.RestaurantResponse;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RestaurantMapper {

    public static RestaurantEntity toPersistenceEntity(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        // Map Restaurant Owners to UserEntities
        Set<User> restaurantOwners = restaurant.getRestaurantOwners();
        if (restaurantOwners != null && !restaurantOwners.isEmpty()) {
            Set<UserEntity> userEntities = restaurantOwners.stream()
                .map(UserMapper::toPersistenceEntity)
                .collect(Collectors.toSet());
            restaurantEntity.setUsers(userEntities);
        }

        // Map Opening Hours to OpeningHoursEntities
        Set<OpeningHours> openingHoursSet = restaurant.getOpeningHoursSet();
        if (openingHoursSet != null && !openingHoursSet.isEmpty()) {
            Set<OpeningHoursEntity> openingHoursEntities = OpeningHoursMapper.toPersistenceEntitySet(openingHoursSet);
            // Set the parent restaurant reference in each OpeningHoursEntity
            for (OpeningHoursEntity opening : openingHoursEntities) {
                opening.setRestaurant(restaurantEntity);
            }
            restaurantEntity.setOpeningHours(openingHoursEntities);
        }

        restaurantEntity.setId(restaurant.getId());
        restaurantEntity.setName(restaurant.getName());
        restaurantEntity.setAddress(restaurant.getAddress());
        restaurantEntity.setCuisineType(restaurant.getCuisineType());

        return restaurantEntity;
    }

    public static Restaurant toDomainEntity(RestaurantEntity restaurantEntity) {
        Set<OpeningHours> openingHoursList = OpeningHoursMapper.toDomainEntityList(restaurantEntity.getOpeningHours());

        // Map users without their restaurants to avoid circular dependency
        Set<User> usersSet = restaurantEntity.getUsers().stream()
            .map(RestaurantMapper::mapUserWithoutRestaurants)
            .collect(java.util.stream.Collectors.toSet());

        return Restaurant.create(
            restaurantEntity.getId(),
            restaurantEntity.getName(),
            restaurantEntity.getAddress(),
            restaurantEntity.getCuisineType(),
            usersSet,
            openingHoursList
        );
    }

    /**
     * Maps a UserEntity to a User domain object without loading the user's restaurants
     * to prevent circular dependency and StackOverflowError.
     */
    private static User mapUserWithoutRestaurants(UserEntity userEntity) {
        return User.create(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getUserIdentification(),
            userEntity.getEmail(),
            userEntity.getAddress(),
            UserCredentialsMapper.toDomainEntity(userEntity.getUserCredentials()),
            userEntity.getProfile(),
            userEntity.getLastUpdate()
        );
    }

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        Set<OpeningHoursDTO> openingHoursDTOSet = OpeningHoursMapper.toResponseDtoSet(restaurant.getOpeningHoursSet());

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
