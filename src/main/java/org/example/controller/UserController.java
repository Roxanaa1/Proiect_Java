package org.example.controller;
import jakarta.validation.Valid;
import org.example.model.dtos.CustomResponseDTO;
import org.example.model.dtos.UserCreateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    List<UserCreateDTO> userCreateDTOList = new ArrayList<>();
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<CustomResponseDTO> createNewUser(@RequestBody @Valid
                                                           UserCreateDTO userCreateDTO,
                                                           BindingResult bindingResult)
    {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors())
        {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        UserSearchDTO userSearchDTO = userService.createUser(userCreateDTO);

        customResponseDTO.setResponseObject(userSearchDTO);
        customResponseDTO.setResponseMessage("User created successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user/getUsersByFirstName/{firstName}")
    public ResponseEntity<CustomResponseDTO> getUsersByFirstName(@PathVariable String firstName)
    { // -> Path param ../23 (23 fiind id-ul)

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        List<UserSearchDTO> foundUsers = userService.findUsersByFirstName(firstName);
        if(Objects.isNull(foundUsers)|| foundUsers.isEmpty())
        {
            customResponseDTO.setResponseMessage("No user was found by this first name.");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
        customResponseDTO.setResponseObject(foundUsers);
        customResponseDTO.setResponseMessage("Users found successfully!");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/user/getUsersById/{id}")
    public ResponseEntity<CustomResponseDTO> getUsersById(@PathVariable Long id)
    { // -> Path param ../23 (23 fiind id-ul)

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        List<UserSearchDTO> foundUsers = userService.findUsersById(id);
        if(Objects.isNull(foundUsers)|| foundUsers.isEmpty())
        {
            customResponseDTO.setResponseMessage("No user was found by this id.");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
        customResponseDTO.setResponseObject(foundUsers);
        customResponseDTO.setResponseMessage("Users found successfully!");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<CustomResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserCreateDTO userCreateDTO,
            BindingResult bindingResult) {

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        UserCreateDTO updatedDTO = userService.updateUser(id, userCreateDTO);
        customResponseDTO.setResponseObject(updatedDTO);
        customResponseDTO.setResponseMessage("User updated successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId)
    {
        userService.deleteUserById(userId);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }
}