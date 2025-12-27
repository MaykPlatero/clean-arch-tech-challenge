package br.com.fiap.clean_arch.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class MenuItem {

    private Long id;
    private String name;
    private Restaurant restaurant;
    private BigDecimal price;
    private boolean deliveryItem;
    private String photoUrl;
    private ZonedDateTime lastUpdate;

    public static MenuItem create(Long id, String name, BigDecimal price, boolean deliveryItem, String photoUrl) {
        MenuItem menuItem = MenuItem.create(name, price, deliveryItem, photoUrl, ZonedDateTime.now());
        menuItem.id = id;
        return menuItem;
    }

    public static MenuItem create(String name, BigDecimal price, boolean deliveryItem, String photoUrl, ZonedDateTime lastUpdate) {
        price = price.setScale(2, RoundingMode.HALF_UP);
        validateMenuItem(name, price, photoUrl);
        MenuItem menuItem = new MenuItem();
        menuItem.name = name;
        menuItem.price = price;
        menuItem.deliveryItem = deliveryItem;
        menuItem.photoUrl = photoUrl;
        menuItem.lastUpdate = lastUpdate;
        return menuItem;
    }

    private static void validateMenuItem(String name, BigDecimal price, String photoUrl) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be bigger than zero");
        }
        if (photoUrl != null && !photoUrl.trim().isEmpty()) {
            if (!photoUrl.startsWith("http://") && !photoUrl.startsWith("https://")) {
                throw new IllegalArgumentException("Photo URL must start with http:// or https://");
            }
        }
    }
}
