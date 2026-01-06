package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {
}
