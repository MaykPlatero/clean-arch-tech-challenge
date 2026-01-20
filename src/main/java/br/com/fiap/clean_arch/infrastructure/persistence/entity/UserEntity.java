package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import br.com.fiap.clean_arch.domain.entities.EProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "user_identification", nullable = false)
    private String userIdentification;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;


    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credentials_id", nullable = false, unique = true)
    private UserCredentialsEntity userCredentials;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) CHECK (profile IN ('CLIENT', 'OWNER', 'ADMIN'))")
    @Enumerated(EnumType.STRING)
    private EProfile profile;

    @Column(name = "last_update")
    private ZonedDateTime lastUpdate;
}
