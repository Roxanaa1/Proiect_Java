import jakarta.persistence.EntityNotFoundException;
import org.example.controller.UserController;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTest
{
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Test
    void deleteUser_Succes()
    {
        // Given
        Long userId = 1L;

        // When
        ResponseEntity<String> response = userController.deleteUser(userId);

        // Then
        verify(userService, times(1)).deleteUserById(userId);
        assertEquals("User deleted", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void deleteUser_Fail() {
        // Given
        Long id = 100L;
        doThrow(new EntityNotFoundException("User not found with id " + id))
                .when(userService).deleteUserById(id);

        // When
        ResponseEntity<String> response = userController.deleteUser(id);

        // Then
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("User not found with id " + id));
    }
}
