package org.example.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="animals")
public class AnimalEntity
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name")
        private String name;
        @Column(name = "age")
        private int age;
        @Column(name = "race")
        private String  race;
        @Column(name = "description")
        private String description;

}
