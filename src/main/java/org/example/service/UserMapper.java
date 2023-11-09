package org.example.service;

import org.example.model.dtos.UserDTO;
import org.example.model.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{

    public UserEntity mapUserDTOtoUserEntity(UserDTO userDTO)
    {
        return new UserEntity(userDTO.getId(),userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getUsername(), userDTO.getEmail(), userDTO.getAge(),
                userDTO.getPassword());
    }

    public UserDTO mapUserEntityToUserDTO(UserEntity userEntity)
    {
        return UserDTO.builder().age(userEntity.getAge()).email(userEntity.getEmail())
                .firstName(userEntity.getFirstName()).lastName(userEntity.getLastName())
                .password(userEntity.getPassword()).id(userEntity.getId()).build();
    }
}