package org.example.service;
import jakarta.persistence.EntityNotFoundException;
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
    @Transactional
    public UserCreateDTO updateUser(Long id, UserCreateDTO userCreateDTO) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id has to be greater than zero");
        }

        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

            // Verifică nulitatea înainte de mapare
            if (existingUser != null) {
                modelMapper.map(userCreateDTO, existingUser);

                // Salvează entitatea actualizată în baza de date
                User updatedUser = userRepository.save(existingUser);

                return modelMapper.map(updatedUser, UserCreateDTO.class);
            } else {
                throw new IllegalStateException("Existing user is null");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("A apărut o excepție: " + e.getMessage());
            return null;
        }
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

