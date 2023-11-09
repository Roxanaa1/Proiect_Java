package org.example.model.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnimalDTO
{
    private Long id;
    private String name;
    private int age;
    private String  race;
    private String description;

}
