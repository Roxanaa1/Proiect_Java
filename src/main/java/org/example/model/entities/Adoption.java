package org.example.model.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data

@Entity(name = "adoption")
@Table(name = "adoptions")
public class Adoption
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long animalId;

    private long userId;

    @ManyToOne
    @JoinColumn(name = "animal__id")
    private AnimalEntity animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user__id")
    private User user;

}