package br.com.fiap.clean_arch.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String userIdentification;
    private String email;
    private String address;
    private UserCredentials userCredentials;
    private EProfile profile;
    private ZonedDateTime lastUpdate;

    public static User create(String name, String userIdentification, String email, String addressUser, UserCredentials userCredentials, EProfile profile, ZonedDateTime lastUpdate) {
        validateUser(name, userIdentification, email, addressUser, userCredentials, profile);
        User user = new User();
        user.setName(name);
        user.setUserIdentification(userIdentification);
        user.setEmail(email);
        user.setAddress(addressUser);
        user.setUserCredentials(userCredentials);
        user.setProfile(profile);
        user.setLastUpdate(lastUpdate);
        return user;
    }

    public static User create(Long id, String name, String userIdentification, String email, String addressUser, UserCredentials userCredentials, EProfile profile, ZonedDateTime lastUpdate) {
        User user = create(name, userIdentification, email, addressUser, userCredentials, profile, lastUpdate);
        user.setId(id);
        return user;
    }

    private static void validateUser(String name, String userIdentification, String email, String address, UserCredentials userCredentials, EProfile profile) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (userIdentification == null || userIdentification.trim().isEmpty()) {
            throw new IllegalArgumentException("User identification is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (address == null) {
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
