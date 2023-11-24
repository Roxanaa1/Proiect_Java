package org.example.service;
import org.example.model.dtos.UserCreateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.model.entities.User;
import org.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper; // custom mapper
    private final ModelMapper modelMapper;

    @Autowired
    UserService(UserRepository userRepository, UserMapper userMapper,
                ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.modelMapper = modelMapper;
    }

    public List<UserSearchDTO> findUsersByFirstName(String firstName) {
        // validate, transform...
        return userRepository.findByFirstName(firstName).stream()
                .map(entity->userMapper.mapUserEntityToUserSearchDTO(entity))
                .collect(Collectors.toList());
    }

    public UserSearchDTO createUser(UserCreateDTO userToCreateDTO) {
        // translate from UserDTO -> UserEntity
        User user = userMapper.mapUserDTOtoUserEntity(userToCreateDTO);
        User createdUser = userRepository.save(user);//salvam userul in db => user entity
        return userMapper.mapUserEntityToUserSearchDTO(createdUser);// translate from UserEntity -> UserDTO
    }

    public void deleteUserById(Long id){
        //validari ex daca exista userul
        userRepository.deleteById(id);
    }

    public UserCreateDTO updateUser(UserCreateDTO userCreateDTO){

        User user = modelMapper.map(userCreateDTO, User.class);
        // userRepository...
        return modelMapper.map(user, UserCreateDTO.class);

    }

}

