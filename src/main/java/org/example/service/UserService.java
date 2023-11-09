package org.example.service;


import org.example.model.dtos.UserDTO;
import org.example.model.entities.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    UserService(UserRepository userRepository, UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> findUsersByFirstName(String firstName)
    {
        // validate, transform...
        return userRepository.findUsersByFirstName(firstName);
    }

    public UserDTO createUser(UserDTO userToCreateDTO)
    {
        // translate from UserDTO -> UserEntity

        UserEntity userEntity = userMapper.mapUserDTOtoUserEntity(userToCreateDTO);

        UserEntity createdUserEntity = userRepository.createUser(userEntity);

        return userMapper.mapUserEntityToUserDTO(createdUserEntity);
    }
    public List<UserDTO> findUsersById(Long id)
    {
        return userRepository.findUsersById(id);
    }


    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        // Setează proprietățile lui userEntity în funcție de userDTO...
        userEntity.setId(id);
        // ...alte setări...

        userRepository.save(userEntity);
        return userDTO; // sau returnează un UserDTO transformat din userEntity actualizat
    }

    public boolean deleteUser(Long id)
    {
        return userRepository.deleteById(id);
    }
}

