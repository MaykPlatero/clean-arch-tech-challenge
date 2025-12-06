package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.RestaurantJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRepositoryAdapter implements RestaurantRepository {
    private final RestaurantJpaRepository jpaRepository;

    public RestaurantRepositoryAdapter(RestaurantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = toEntity(restaurant);
        RestaurantEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

//    private RestaurantEntity toEntity(Restaurant restaurant) {
//        return new RestaurantEntity(
//            restaurant.getId(),
//            restaurant.getName(),
//            toAddressEmbeddable(restaurant.getAddress()),
//            restaurant.getCuisineType(),
//            toUserEntity(restaurant.getRestaurantOwner()),
//            toOpeningHoursEntityList(restaurant.getOpeningHoursList())
//        );
//    }
//
//    private Restaurant toDomain(RestaurantEntity entity) {
//        return new Restaurant(
//            entity.getId(),
//            entity.getName(),
//            toAddress(entity.getAddress()),
//            entity.getCuisineType(),
//            toUser(entity.getRestaurantOwner()),
//            toOpeningHoursList(entity.getOpeningHoursList())
//        );
//    }
}

