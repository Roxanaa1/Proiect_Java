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

        private String firstName;
        private String lastName;
        private String username;
        //    private JobProfile jobProfile;
        @NotBlank(message = "Emailul nu poate fi null.")
        private String email;

        private int age;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
}
