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
        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;
        @Column(name = "username")
        private String username;
        @Column(name = "email")
        private String email;
        @Column(name = "age")
        private int age;
        @Column(name = "password")
        private String password;

        @OneToMany(mappedBy = "user")
        private List<Adoption> adoptions;

}