package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.MenuItemJpaRepository;
import br.com.fiap.clean_arch.presentation.mappers.MenuItemMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MenuItemRepositoryAdapter implements MenuItemRepository {
    private final MenuItemJpaRepository menuItemJpaRepository;

    public MenuItemRepositoryAdapter(MenuItemJpaRepository menuItemJpaRepository) {
        this.menuItemJpaRepository = menuItemJpaRepository;
    }

    @Override
    @Transactional
    public MenuItem save(MenuItem menuItem) {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(menuItem.getId());
        entity.setName(menuItem.getName());
        entity.setDescription(menuItem.getDescription());
        entity.setRestaurantId(menuItem.getRestaurantId());
        entity.setPrice(menuItem.getPrice());
        entity.setDeliveryItem(menuItem.isDeliveryItem());
        entity.setPhotoUrl(menuItem.getPhotoUrl());
        entity.setLastUpdate(menuItem.getLastUpdate());

        MenuItemEntity savedEntity = menuItemJpaRepository.save(entity);

        return MenuItem.create(
                savedEntity.getId(),
                savedEntity.getRestaurantId(),
                savedEntity.getName(),
                savedEntity.getDescription(),
                savedEntity.getPrice(),
                savedEntity.isDeliveryItem(),
                savedEntity.getPhotoUrl()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItem findById(Long id) {
        return menuItemJpaRepository.findById(id)
                .map(MenuItemMapper::mapToDomain)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItem> findByRestaurantId(Long restaurantId) {
        return menuItemJpaRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(MenuItemMapper::mapToDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        menuItemJpaRepository.deleteById(id);
    }
}
