package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpeningHoursJpaRepository extends JpaRepository<OpeningHoursEntity, Long> {
    List<OpeningHoursEntity> findByRestaurantId(Long restaurantId);
}