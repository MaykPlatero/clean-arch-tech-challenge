package br.com.fiap.clean_arch.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserCredentials {
    private Long id;
    private String username;
    private String password;
    private ZonedDateTime lastUpdate;

    public static UserCredentials create(String username, String password, ZonedDateTime lastUpdate) {
        validateCredentials(username, password);
        UserCredentials credentials = new UserCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setLastUpdate(lastUpdate);
        return credentials;
    }

    public static UserCredentials create(Long id, String username, String password, ZonedDateTime lastUpdate) {
        UserCredentials credentials = create(username, password, lastUpdate);
        credentials.setId(id);
        return credentials;
    }

    private static void validateCredentials(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }
}
