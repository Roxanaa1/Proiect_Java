package org.example.model.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class UserSearchDTO
{
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private int age;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
