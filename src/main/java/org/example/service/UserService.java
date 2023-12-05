package org.example.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.dtos.UserCreateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.model.dtos.UserUpdateDTO;
import org.example.model.entities.User;
import org.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    @Autowired
    UserService(UserRepository userRepository, UserMapper userMapper, ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.modelMapper = modelMapper;
    }
    public UserSearchDTO createUser(UserCreateDTO userToCreateDTO)
    {
        // translate from UserDTO -> UserEntity
        User user = userMapper.mapUserDTOtoUserEntity(userToCreateDTO);
        User createdUser = userRepository.save(user);//salvam userul in db => user entity
        return userMapper.mapUserEntityToUserSearchDTO(createdUser);// translate from UserEntity -> UserDTO
    }
    public List<UserSearchDTO> findUsersByFirstName(String firstName)
    {
        // validate, transform...
        if(firstName==null)
        {
            throw new NullPointerException("firstName can not be null");
        }
        return userRepository.findByFirstName(firstName).stream()
                .map(entity->userMapper.mapUserEntityToUserSearchDTO(entity))
                .collect(Collectors.toList());
    }
    public List<UserSearchDTO> findUsersById(Long id)
    {
        // validate, transform...
        if(id==null || id<=0)
        {
            throw new IllegalArgumentException("Id has to be greater than zero");
        }
        return userRepository.findById( id).stream()
                .map(entity->userMapper.mapUserEntityToUserSearchDTO(entity))
                .collect(Collectors.toList());
    }
    public List<UserSearchDTO> findUsers(Long id, String firstName, String lastName) {
        List<User> userEntities;

        if (id != null && id > 0) {
            Optional<User> userEntity = userRepository.findById(id);
            userEntities = userEntity.map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            userEntities = userRepository.findByFirstNameAndLastName(firstName, lastName);
        }

        return userEntities.stream()
                .map(userMapper::mapUserEntityToUserSearchDTO)
                .collect(Collectors.toList());
    }

    //    public UserUpdateDTO updateUser(Long id, UserUpdateDTO userUpdateDTO)
//    {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
//
//        // Actualizeaza entitatea User cu datele primite din UserUpdateDTO
//        existingUser.setFirstName(userUpdateDTO.getFirstName());
//        existingUser.setLastName(userUpdateDTO.getLastName());
//        existingUser.setUsername(userUpdateDTO.getUsername());
//        existingUser.setEmail(userUpdateDTO.getEmail());
//        existingUser.setAge(userUpdateDTO.getAge());
//        existingUser.setPassword(userUpdateDTO.getPassword());
//
//        // Salveaza utilizatorul actualizat în baza de date
//        User updatedUser = userRepository.save(existingUser);
//
//        // Returneaza un DTO cu datele utilizatorului actualizat
//        return userMapper.mapUserEntityToUserUpdateDTO(updatedUser);
//    }
public UserSearchDTO updateUser(Long id, UserUpdateDTO userUpdateDTO)
{
    User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    userMapper.updateUserFromDto(userUpdateDTO, existingUser);

    User updatedUser = userRepository.save(existingUser);

    return userMapper.mapUserEntityToUserSearchDTO(updatedUser);
}

    public void deleteUserById(Long id)
    {
        //validari daca exista userul,etc...
        if (id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id has to be greater than zero");
        }
        if (!userRepository.existsById(id))
        {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

}

