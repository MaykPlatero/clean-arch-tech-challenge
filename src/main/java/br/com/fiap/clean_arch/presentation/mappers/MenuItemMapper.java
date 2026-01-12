package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import br.com.fiap.clean_arch.presentation.dto.response.MenuItemResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MenuItemMapper {

    public static MenuItem mapToDomain(MenuItemEntity entity) {
        return MenuItem.create(
                entity.getId(),
                entity.getRestaurant() != null ? entity.getRestaurant().getId() : null,
                entity.getName(),
                entity.getPrice(),
                entity.isDeliveryItem(),
                entity.getPhotoUrl()
        );
    }

    public static MenuItemResponse toResponse(MenuItem menuItem) {
        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getRestaurantId(),
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.isDeliveryItem(),
                menuItem.getPhotoUrl(),
                menuItem.getLastUpdate()
        );
    }
}
