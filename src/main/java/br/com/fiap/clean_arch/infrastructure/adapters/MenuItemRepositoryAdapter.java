package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.MenuItemJpaRepository;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.RestaurantJpaRepository;
import br.com.fiap.clean_arch.presentation.mappers.MenuItemMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MenuItemRepositoryAdapter implements MenuItemRepository {
    private final MenuItemJpaRepository menuItemJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;

    public MenuItemRepositoryAdapter(MenuItemJpaRepository menuItemJpaRepository,
                                     RestaurantJpaRepository restaurantJpaRepository) {
        this.menuItemJpaRepository = menuItemJpaRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    @Transactional
    public MenuItem save(MenuItem menuItem) {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(menuItem.getId());
        entity.setName(menuItem.getName());
        entity.setPrice(menuItem.getPrice());
        entity.setDeliveryItem(menuItem.isDeliveryItem());
        entity.setPhotoUrl(menuItem.getPhotoUrl());
        entity.setLastUpdate(menuItem.getLastUpdate());

        // Get the restaurant reference without loading it
        if (menuItem.getRestaurantId() != null) {
            RestaurantEntity restaurantEntity = restaurantJpaRepository.getReferenceById(menuItem.getRestaurantId());
            entity.setRestaurant(restaurantEntity);
        }

        MenuItemEntity savedEntity = menuItemJpaRepository.save(entity);

        return MenuItem.create(
                savedEntity.getId(),
                savedEntity.getRestaurant() != null ? savedEntity.getRestaurant().getId() : null,
                savedEntity.getName(),
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
}
