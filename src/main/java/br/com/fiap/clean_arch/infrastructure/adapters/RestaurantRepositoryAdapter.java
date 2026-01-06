package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.RestaurantJpaRepository;
import br.com.fiap.clean_arch.presentation.mappers.RestaurantMapper;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRepositoryAdapter implements RestaurantRepository {
    private final RestaurantJpaRepository jpaRepository;

    public RestaurantRepositoryAdapter(RestaurantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity entity = RestaurantMapper.toPersistenceEntity(restaurant);
        RestaurantEntity saved = jpaRepository.save(entity);
        return RestaurantMapper.toDomainEntity(saved);
    }
}

