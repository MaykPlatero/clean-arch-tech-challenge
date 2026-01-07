package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.OpeningHoursJpaRepository;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.RestaurantJpaRepository;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.UserJpaRepository;
import br.com.fiap.clean_arch.presentation.mappers.OpeningHoursMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class RestaurantRepositoryAdapter implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final OpeningHoursJpaRepository openingHoursJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public RestaurantRepositoryAdapter(
            RestaurantJpaRepository restaurantJpaRepository,
            OpeningHoursJpaRepository openingHoursJpaRepository,
            UserJpaRepository userJpaRepository) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.openingHoursJpaRepository = openingHoursJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        // Create and save the restaurant entity first
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurant.getId());
        restaurantEntity.setName(restaurant.getName());
        restaurantEntity.setAddress(restaurant.getAddress());
        restaurantEntity.setCuisineType(restaurant.getCuisineType());

        // Save restaurant first to get the ID
        RestaurantEntity savedRestaurant = restaurantJpaRepository.save(restaurantEntity);

        // Handle the ManyToMany relationship with users
        Set<User> restaurantOwners = restaurant.getRestaurantOwners();
        if (restaurantOwners != null && !restaurantOwners.isEmpty()) {
            for (User owner : restaurantOwners) {
                if (owner.getId() != null) {
                    // Fetch the actual user entity from DB
                    UserEntity userEntity = userJpaRepository.findById(owner.getId())
                            .orElseThrow(() -> new IllegalArgumentException("User with ID " + owner.getId() + " not found."));
                    // Add restaurant to user's restaurants (owning side of the relationship)
                    userEntity.getRestaurants().add(savedRestaurant);
                    userJpaRepository.save(userEntity);
                }
            }
        }

        // Save Opening Hours
        Set<OpeningHours> openingHoursSet = restaurant.getOpeningHoursSet();
        if (openingHoursSet != null && !openingHoursSet.isEmpty()) {
            Set<OpeningHoursEntity> openingHoursEntities = OpeningHoursMapper.toPersistenceEntitySet(openingHoursSet);
            for (OpeningHoursEntity opening : openingHoursEntities) {
                opening.setRestaurant(savedRestaurant);
                openingHoursJpaRepository.save(opening);
            }
        }

        // Map back to domain without triggering circular dependencies
        return mapToDomainSimple(savedRestaurant, restaurant.getRestaurantOwners(), openingHoursSet);
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurant findById(Long id) {
        return restaurantJpaRepository.findById(id)
                .map(this::mapToDomainFromEntity)
                .orElse(null);
    }

    /**
     * Maps RestaurantEntity to Restaurant domain object without triggering circular dependencies.
     * Uses the original domain objects instead of fetching from the entity to avoid lazy loading issues.
     */
    private Restaurant mapToDomainSimple(RestaurantEntity entity, Set<User> owners, Set<OpeningHours> openingHours) {
        return Restaurant.create(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getCuisineType(),
                owners != null ? owners : Set.of(),
                openingHours != null ? openingHours : Set.of()
        );
    }

    /**
     * Maps RestaurantEntity to Restaurant domain object for findById.
     * Maps users and opening hours from the entity without circular dependencies.
     */
    private Restaurant mapToDomainFromEntity(RestaurantEntity entity) {
        // Map opening hours
        Set<OpeningHours> openingHoursSet = OpeningHoursMapper.toDomainEntityList(entity.getOpeningHours());

        // Map users without their restaurants to avoid circular dependency
        Set<User> usersSet = entity.getUsers().stream()
                .map(this::mapUserWithoutRestaurants)
                .collect(java.util.stream.Collectors.toSet());

        return Restaurant.create(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getCuisineType(),
                usersSet,
                openingHoursSet
        );
    }

    /**
     * Maps a UserEntity to a User domain object without loading the user's restaurants
     * to prevent circular dependency and StackOverflowError.
     */
    private User mapUserWithoutRestaurants(UserEntity userEntity) {
        return User.create(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getUserIdentification(),
                userEntity.getEmail(),
                userEntity.getAddress(),
                br.com.fiap.clean_arch.domain.entities.UserCredentials.create(
                        userEntity.getUserCredentials().getId(),
                        userEntity.getUserCredentials().getUsername(),
                        userEntity.getUserCredentials().getPassword(),
                        userEntity.getUserCredentials().getLastUpdate()
                ),
                userEntity.getProfile(),
                userEntity.getLastUpdate()
        );
    }
}
