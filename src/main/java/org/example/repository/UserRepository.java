package org.example.repository;

import lombok.Setter;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Setter
@Repository
public class UserRepository
{

    private UserDTO userDTO1 = new UserDTO(1L,"alex","Jhon","last","email",23,"password");
    private UserDTO userDTO2 = new UserDTO(2L,"Mike","Tyson","last","email@asdwed", 30, "pass1234");
    private UserDTO userDTO3 = new UserDTO(1L,"Erica","Smith","Jhon","email@test.fom",40, "wefwefwef");

    private List<UserEntity> userEntities = new ArrayList<>();
    public List<UserDTO> findUsersByFirstName(String firstName)
    {
        //run sql query to get user by username
        return Stream.of(userDTO1, userDTO2, userDTO3).filter(userDTO -> userDTO.getFirstName()
                .equals(firstName)).toList();
    }

    public UserEntity createUser(UserEntity userToSaveIdDB)
    {
        userEntities.add(userToSaveIdDB);
        return userToSaveIdDB;
    }
    public List<UserDTO> findUsersById(Long id)
    {
        return Stream.of(userDTO1, userDTO2, userDTO3)
                .filter(userDTO -> userDTO.getId() == id)
                .toList();
    }

    public Optional<UserEntity> findById(Long id)
    {
        return userEntities.stream().filter(u -> u.getId()==id).findFirst();
    }

    public UserEntity save(UserEntity user)
    {
        userEntities.add(user);
        return user;
    }

    public boolean deleteById(Long id)
    {
        return userEntities.removeIf(u -> u.getId().equals(id));
    }

}
