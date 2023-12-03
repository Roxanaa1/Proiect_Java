package org.example.service;
import org.example.model.dtos.UserCreateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.model.dtos.UserUpdateDTO;
import org.example.model.entities.User;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {

    public User mapUserDTOtoUserEntity(UserCreateDTO userCreateDTO){
        User user= new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        user.setAge(userCreateDTO.getAge());
        user.setEmail(userCreateDTO.getEmail());
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        return user;
    }
    public User updateUserFromDto(UserUpdateDTO userUpdateDTO, User existingUser) {
        existingUser.setUsername(userUpdateDTO.getUsername());
        existingUser.setPassword(userUpdateDTO.getPassword());
        existingUser.setAge(userUpdateDTO.getAge());
        existingUser.setEmail(userUpdateDTO.getEmail());
        existingUser.setFirstName(userUpdateDTO.getFirstName());
        existingUser.setLastName(userUpdateDTO.getLastName());
        return existingUser;
    }

    public UserUpdateDTO mapUserEntityToUserUpdateDTO(User user){
        return UserUpdateDTO.builder().id(user.getId()).age(user.getAge()).email(user.getEmail())
                .username(user.getUsername()).firstName(user.getFirstName())
                .lastName(user.getLastName()).password(user.getPassword()).build();
    }
    public UserCreateDTO mapUserEntityToUserDTO(User user){
        return UserCreateDTO.builder().age(user.getAge()).email(user.getEmail())
                .username(user.getUsername()).firstName(user.getFirstName())
                .lastName(user.getLastName()).password(user.getPassword()).build();
    }

    public UserSearchDTO mapUserEntityToUserSearchDTO(User user){
        return UserSearchDTO.builder().id(user.getId()).age(user.getAge()).email(user.getEmail())
                .username(user.getUsername()).firstName(user.getFirstName())
                .lastName(user.getLastName()).password(user.getPassword()).build();
    }

}