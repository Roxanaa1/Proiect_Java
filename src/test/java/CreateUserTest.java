import org.example.controller.UserController;
import org.example.model.dtos.CustomResponseDTO;
import org.example.model.dtos.UserCreateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    @Test
    void createNewUser_Success() {
        // Given
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        UserSearchDTO userSearchDTO = new UserSearchDTO();
        given(bindingResult.hasErrors()).willReturn(false);
        given(userService.createUser(any(UserCreateDTO.class))).willReturn(userSearchDTO);

        // When
        ResponseEntity<CustomResponseDTO> response = userController.createNewUser(userCreateDTO, bindingResult);

        // Then
        assertEquals(CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userSearchDTO, response.getBody().getResponseObject());
    }

    @Test
    void createNewUser_Fail() {
        // Given
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        given(bindingResult.hasErrors()).willReturn(true);
        given(bindingResult.getFieldError()).willReturn(new FieldError("user", "name", "must not be empty"));

        // When
        ResponseEntity<CustomResponseDTO> response = userController.createNewUser(userCreateDTO, bindingResult);

        // Then
        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("must not be empty", response.getBody().getResponseMessage());
    }
}
