package org.example.controller;
import jakarta.validation.Valid;
import org.example.model.dtos.CustomResponseDTO;
import org.example.model.dtos.UserDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController
{

    List<UserDTO> userDTOList = new ArrayList<>();
    private final UserService userService;

    @Autowired
    UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping(path = "/user")//POST-creaza alt user
    public ResponseEntity<CustomResponseDTO> createNewUser(
            @RequestBody
            @Valid
            UserDTO userDTO,
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
        userService.createUser(userDTO);
        customResponseDTO.setResponseObject(userDTO);
        userDTOList.add(userDTO);
        customResponseDTO.setResponseMessage("User created successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/getUsersByFirstName/{firstName}")
//    public List<User> getUsersByFirstName(@RequestParam String firstName){ -> Query param ?firstName=alex
    public List<UserDTO> getUsersByFirstName(@PathVariable String firstName)
    { // -> Path param ../23 (23 fiind id-ul)
        return userService.findUsersByFirstName(firstName);
    }
    @GetMapping("/getUsersById/{id}")
    public List<UserDTO> getUserById(@PathVariable Long id)
    {
        return userService.findUsersById(id);
    }

    @PutMapping(path = "/user/{id}")//PUT-actualizeaza
    public ResponseEntity<CustomResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserDTO userDTO,
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

        userService.updateUser(id, userDTO);
        customResponseDTO.setResponseObject(userDTO);
        customResponseDTO.setResponseMessage("User updated successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<CustomResponseDTO> deleteUser(@PathVariable Long id)
    {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (userService.deleteUser(id))
        {
            customResponseDTO.setResponseMessage("User deleted successfully");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
        } else
        {
            customResponseDTO.setResponseMessage("User not found");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
    }


}

