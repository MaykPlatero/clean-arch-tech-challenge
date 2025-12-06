package br.com.fiap.clean_arch.domain.entities;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Address {
    Long id;
    String street;
    int number;
    String complement;
    String neighborhood;
    String city;
    String state;
    String country;
    String cep;
    private ZonedDateTime lastUpdate;

    public Address(Long id, String street, int number, String complement, String neighborhood, String city, String state, String country, String cep, ZonedDateTime lastUpdate) {
        validateAddress(street, number, city, state, country, cep);
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.cep = cep;
        this.lastUpdate = lastUpdate;
    }

    private static void validateAddress(String street, int number, String city, String state, String country, String cep) {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State is required");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }
    }
}
