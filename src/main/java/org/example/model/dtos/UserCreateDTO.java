package org.example.model.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class UserCreateDTO {
        @NotBlank(message = "First name can not be blank")
        private String firstName;
        @NotBlank(message = "Last name can not be blank")
        private String lastName;
        @NotBlank(message = "Username can not be blank")
        private String username;
        @NotBlank(message = "E-mail can not be blank")
        private String email;
        @Min(value = 18, message = "Age must be at least 18")
        private int age;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
}
