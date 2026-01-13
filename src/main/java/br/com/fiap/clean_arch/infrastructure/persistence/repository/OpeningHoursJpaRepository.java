package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursJpaRepository extends JpaRepository<OpeningHoursEntity, Long> {
    List<OpeningHoursEntity> findByRestaurantId(Long restaurantId);
}
