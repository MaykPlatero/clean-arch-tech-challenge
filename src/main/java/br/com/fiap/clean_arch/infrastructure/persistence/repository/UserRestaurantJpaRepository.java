package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRestaurantJpaRepository extends JpaRepository<UserRestaurantEntity, Long> {
    List<UserRestaurantEntity> findByRestaurantId(Long restaurantId);
    List<UserRestaurantEntity> findByUserId(Long userId);
}
