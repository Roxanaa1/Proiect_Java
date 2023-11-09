package org.example.service;

import org.example.model.dtos.AnimalDTO;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.AnimalEntity;
import org.example.model.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper
{
    public AnimalEntity mapAnimalDTOtoAnimalEntity(AnimalDTO animalDTO)
    {
        return new AnimalEntity(animalDTO.getId(),animalDTO.getName(),animalDTO.getAge(), animalDTO.getRace(),
                animalDTO.getDescription());
    }

    public AnimalDTO mapAnimalEntityToAnimalDTO(AnimalEntity animalEntity)
    {
        return AnimalDTO.builder().id(animalEntity.getId()).description(animalEntity.getDescription())
                .age(animalEntity.getAge()).race(animalEntity.getRace())
                .name(animalEntity.getName()).build();
    }
}
