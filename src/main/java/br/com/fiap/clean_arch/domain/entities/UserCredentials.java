package br.com.fiap.clean_arch.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
public class UserCredentials {
    private Long id;
    private String username;
    private String password;
    private ZonedDateTime lastUpdate;
}
