package org.example.model.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private int age;
        private String password;

        @OneToMany(mappedBy = "user")
        private List<Adoption> adoptions;

}