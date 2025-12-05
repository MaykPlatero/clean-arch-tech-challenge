package br.com.fiap.clean_arch.domain.entities;

import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String cuisineType;

    public Restaurant(String name, String address, String cuisineType) {
        validateName(name);
        validateAddress(address);
        validateCuisineType(cuisineType);
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
    }

    public Restaurant(Long id, String name, String address, String cuisineType) {
        this(name, address, cuisineType);
        this.id = id;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("Restaurant name is required");
        }
        if (name.length() < 3) {
            throw new DomainException("Restaurant name must have at least 3 characters");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new DomainException("Address is required");
        }
    }

    private void validateCuisineType(String cuisineType) {
        if (cuisineType == null || cuisineType.trim().isEmpty()) {
            throw new DomainException("Cuisine type is required");
        }
    }
}
