import jakarta.persistence.EntityNotFoundException;
import org.example.controller.UserController;
import org.example.model.dtos.UserSearchDTO;
import org.example.model.dtos.UserUpdateDTO;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
@ExtendWith(MockitoExtension.class)
public class UpdateUserTest
{

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    // Test pentru actualizare reusita
    @Test
    void updateUser_Success() {
        Long id = 1L;
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setFirstName("Irina");


        UserSearchDTO updatedUser = new UserSearchDTO();


        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.updateUser(eq(id), any(UserUpdateDTO.class))).thenReturn(updatedUser);

        ResponseEntity<?> response = userController.updateUser(id, userUpdateDTO, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    // Test pentru date invalide
    @Test
    void updateUser_BadRequest() {
        Long id = 1L;
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = userController.updateUser(id, userUpdateDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // Test pentru cazul în care entitatea nu este gasita
    @Test
    void updateUser_NotFound() {
        Long id = 1L;
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.updateUser(eq(id), any(UserUpdateDTO.class))).thenThrow(new EntityNotFoundException("User not found"));

        ResponseEntity<?> response = userController.updateUser(id, userUpdateDTO, bindingResult);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }
}
