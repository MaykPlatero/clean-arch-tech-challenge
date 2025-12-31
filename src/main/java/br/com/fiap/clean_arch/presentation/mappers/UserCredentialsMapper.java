package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserCredentialsEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserCredentialsMapper {
    public static UserCredentials toDomainEntity(UserCredentialsEntity userCredentialsEntity) {
        return UserCredentials.create(userCredentialsEntity.getId(),
                userCredentialsEntity.getUsername(),
                userCredentialsEntity.getPassword(),
                userCredentialsEntity.getLastUpdate());
    }

    public static UserCredentialsEntity toPersistenceEntity(UserCredentials userCredentials) {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setUsername(userCredentials.getUsername());
        userCredentialsEntity.setPassword(userCredentials.getPassword());
        userCredentialsEntity.setLastUpdate(userCredentials.getLastUpdate());
        // should user be added to the userCredentialsEntity too??

        return userCredentialsEntity;
    }
}
