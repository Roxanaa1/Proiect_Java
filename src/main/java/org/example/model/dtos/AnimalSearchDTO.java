package org.example.model.dtos;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class AnimalSearchDTO
{
    private Long id;
    private String name;
    private int age;
    private String  race;
    private String description;

}
