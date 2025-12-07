package br.com.fiap.clean_arch.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Address {
    private Long id;
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String cep;
    private ZonedDateTime lastUpdate;

    public static Address create(String street, int number, String complement, String neighborhood, String city, String state, String country, String cep, ZonedDateTime lastUpdate) {
        validateAddress(street, number, city, state, country, cep);
        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setComplement(complement);
        address.setNeighborhood(neighborhood);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setCep(cep);
        address.setLastUpdate(lastUpdate);
        return address;
    }

    public static Address create(Long id, String street, int number, String complement, String neighborhood, String city, String state, String country, String cep, ZonedDateTime lastUpdate) {
        Address address = create(street, number, complement, neighborhood, city, state, country, cep, lastUpdate);
        address.setId(id);
        return address;
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
