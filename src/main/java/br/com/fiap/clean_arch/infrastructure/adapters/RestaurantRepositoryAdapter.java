package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.*;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.*;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RestaurantRepositoryAdapter implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final UserRestaurantJpaRepository userRestaurantJpaRepository;
    private final OpeningHoursJpaRepository openingHoursJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public RestaurantRepositoryAdapter(
            RestaurantJpaRepository restaurantJpaRepository,
            UserRestaurantJpaRepository userRestaurantJpaRepository,
            OpeningHoursJpaRepository openingHoursJpaRepository,
            UserJpaRepository userJpaRepository) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.userRestaurantJpaRepository = userRestaurantJpaRepository;
        this.openingHoursJpaRepository = openingHoursJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(restaurant.getId());
        entity.setName(restaurant.getName());
        entity.setAddress(restaurant.getAddress());
        entity.setCuisineType(restaurant.getCuisineType());
        RestaurantEntity saved = restaurantJpaRepository.save(entity);
        
        for (User owner : restaurant.getRestaurantOwners()) {
            UserRestaurantEntity ur = new UserRestaurantEntity();
            ur.setUserId(owner.getId());
            ur.setRestaurantId(saved.getId());
            userRestaurantJpaRepository.save(ur);
        }
        
        for (OpeningHours oh : restaurant.getOpeningHoursSet()) {
            OpeningHoursEntity ohEntity = new OpeningHoursEntity();
            ohEntity.setRestaurantId(saved.getId());
            ohEntity.setDayOfWeek(oh.getDayOfWeek());
            ohEntity.setOpenTime(oh.getOpenTime());
            ohEntity.setCloseTime(oh.getCloseTime());
            openingHoursJpaRepository.save(ohEntity);
        }
        
        return findById(saved.getId());
    }
    
    @Override
    public Restaurant findById(Long id) {
        RestaurantEntity entity = restaurantJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
        
        List<UserRestaurantEntity> userRestaurants = 
            userRestaurantJpaRepository.findByRestaurantId(id);
        
        Set<User> owners = userRestaurants.stream()
            .map(ur -> userJpaRepository.findById(ur.getUserId()).orElseThrow())
            .map(UserMapper::toDomainEntity)
            .collect(Collectors.toSet());
        
        List<OpeningHoursEntity> ohEntities = 
            openingHoursJpaRepository.findByRestaurantId(id);
        Set<OpeningHours> openingHours = ohEntities.stream()
            .map(oh -> new OpeningHours(oh.getId(), oh.getRestaurantId(), 
                oh.getDayOfWeek(), oh.getOpenTime(), oh.getCloseTime()))
            .collect(Collectors.toSet());
        
        return Restaurant.create(entity.getId(), entity.getName(), 
            entity.getAddress(), entity.getCuisineType(), owners, openingHours);
    }
}
