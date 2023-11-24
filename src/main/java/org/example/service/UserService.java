package org.example.service;
import jakarta.transaction.Transactional;
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
public class UserService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper; // custom mapper
    private final ModelMapper modelMapper;
    @Autowired
    UserService(UserRepository userRepository, UserMapper userMapper, ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public UserCreateDTO updateUser(Long id, UserCreateDTO userCreateDTO) {
        // Verifică dacă utilizatorul există în baza de date
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        modelMapper.map(userCreateDTO, existingUser);

        // Salvează entitatea actualizată în baza de date
        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserCreateDTO.class);
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
        return userRepository.findByFirstName(firstName).stream()
                .map(entity->userMapper.mapUserEntityToUserSearchDTO(entity))
                .collect(Collectors.toList());
    }
    public List<UserSearchDTO> findUsersById(Long id)
    {
        // validate, transform...
        return userRepository.findById( id).stream()
                .map(entity->userMapper.mapUserEntityToUserSearchDTO(entity))
                .collect(Collectors.toList());
    }
    public void deleteUserById(Long id)
    {
        //validari ex daca exista userul
        userRepository.deleteById(id);
    }

}

