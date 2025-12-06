package br.com.fiap.clean_arch.domain.entities;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class User {
    private Long id;
    private String name;
    private String userIdentification;
    private String email;
    private Address addressUser;
    private UserCredentials userCredentials;
    private EProfile profile;
    private ZonedDateTime lastUpdate;

    public User(Long id, String name, String userIdentification, String email, Address addressUser, UserCredentials userCredentials, EProfile profile, ZonedDateTime lastUpdate) {
        validateUser(name, userIdentification, email, addressUser, userCredentials, profile);
        this.id = id;
        this.name = name;
        this.userIdentification = userIdentification;
        this.email = email;
        this.addressUser = addressUser;
        this.userCredentials = userCredentials;
        this.profile = profile;
        this.lastUpdate = lastUpdate;
    }

    private static void validateUser(String name, String userIdentification, String email, Address addressUser, UserCredentials userCredentials, EProfile profile) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (userIdentification == null || userIdentification.trim().isEmpty()) {
            throw new IllegalArgumentException("User identification is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (addressUser == null) {
            throw new IllegalArgumentException("Address is required");
        }
        if (userCredentials == null) {
            throw new IllegalArgumentException("User credentials are required");
        }
        if (profile == null) {
            throw new IllegalArgumentException("Profile is required");
        }
    }
}
