package org.example.model.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserUpdateDTO
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
