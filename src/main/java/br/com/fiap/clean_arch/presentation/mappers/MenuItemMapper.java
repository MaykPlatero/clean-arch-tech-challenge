package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
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
}
