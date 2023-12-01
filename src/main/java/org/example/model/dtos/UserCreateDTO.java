package org.example.model.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class UserCreateDTO
{

        private long id;

        @NotNull
        @NotBlank(message = "First name can not be blank")
        private String firstName;

        @NotNull
        @NotBlank(message = "Last name can not be blank")
        private String lastName;

        @NotNull
        @NotBlank(message = "Username can not be blank")
        private String username;

        @NotNull
        @NotBlank(message = "E-mail can not be blank")
        private String email;

        @NotNull
        @Min(value = 18, message = "Age must be at least 18")
        private int age;

        @NotNull
        @NotBlank(message = "Password can not be blank")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
}
