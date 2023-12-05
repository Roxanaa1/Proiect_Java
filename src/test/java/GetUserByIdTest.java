import org.example.controller.UserController;
import org.example.model.dtos.CustomResponseDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdTest
{
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUsersByIdUserFound() {
        // Given
        Long id = 1L;
        UserSearchDTO user1 = new UserSearchDTO();
        user1.setId(1L);
        user1.setFirstName("Iacobita");
        user1.setLastName("Andreea");
        user1.setAge(17);
        List<UserSearchDTO> foundUsers = Arrays.asList(user1);
        when(userService.findUsersById(id)).thenReturn(foundUsers);

        // When
        ResponseEntity<CustomResponseDTO> response = userController.getUsersById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Users found successfully!", response.getBody().getResponseMessage());
    }

    @Test
    void getUsersByIdUserNotFound() {
        // Given
        Long id = 1L;
        when(userService.findUsersById(id)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<CustomResponseDTO> response = userController.getUsersById(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No user was found by this id.", response.getBody().getResponseMessage());
    }

}
