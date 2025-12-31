package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.application.dto.CreateUserDTO;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.presentation.dto.UserResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {
    public static User toDomainEntity(UserEntity userEntity) {
        return User.create(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getUserIdentification(),
                userEntity.getEmail(),
                null, // Map address here
                UserCredentialsMapper.toDomainEntity(userEntity.getUserCredentials()),
                userEntity.getProfile(),
                userEntity.getLastUpdate()
        );
    }

    public static User toDomainEntity(CreateUserDTO createUserDTO) throws IllegalArgumentException {
        EProfile profile = EProfile.valueOf(createUserDTO.profile());
        UserCredentials userCredentials = UserCredentials.create(createUserDTO.username(), createUserDTO.password(),null);

        return User.create(
                null,
                createUserDTO.name(),
                createUserDTO.userIdentification(),
                createUserDTO.email(),
                null, // Map address here
                userCredentials,
                profile,
                null
        );
    }

    public static UserEntity toPersistenceEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setUserIdentification(user.getUserIdentification());
        userEntity.setEmail(user.getEmail());
        // Map address here
        userEntity.setUserCredentials(UserCredentialsMapper.toPersistenceEntity(user.getUserCredentials()));
        userEntity.setProfile(user.getProfile());
        userEntity.setLastUpdate(user.getLastUpdate());
        return userEntity;
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserIdentification(),
                user.getProfile().name()
        );
    }
}
