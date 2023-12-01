package org.example.model.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animal")
@Table(name="animals")
public class AnimalEntity
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private int age;

        private String  race;

        private String description;

        @OneToMany(mappedBy = "animal")
        private List<Adoption> adoptions;

}
