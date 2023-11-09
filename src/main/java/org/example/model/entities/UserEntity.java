package org.example.model.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity
{
        private Long id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private int age;
        private String password;

        public void set(Long newId) {
        }
}