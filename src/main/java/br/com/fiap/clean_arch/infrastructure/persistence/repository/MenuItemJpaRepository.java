package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {
}
