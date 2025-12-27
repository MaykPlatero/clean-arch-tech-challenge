package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "menu_items")
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "delivery_item")
    private boolean deliveryItem;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "last_update")
    private ZonedDateTime lastUpdate;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public RestaurantEntity getRestaurant() { return restaurant; }
    public void setRestaurant(RestaurantEntity restaurant) { this.restaurant = restaurant; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public boolean isDeliveryItem() { return deliveryItem; }
    public void setDeliveryItem(boolean deliveryItem) { this.deliveryItem = deliveryItem; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public ZonedDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(ZonedDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
}

