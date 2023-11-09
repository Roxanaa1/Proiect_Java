package org.example.model.entities;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnimalEntity
{
        private Long id;
        private String name;
        private int age;
        private String  race;
        private String description;

}
