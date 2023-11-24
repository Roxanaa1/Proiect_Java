package org.example.service;
import org.example.model.dtos.AnimalSearchDTO;
import org.example.model.entities.AnimalEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper
{
    public AnimalEntity mapAnimalDTOtoAnimalEntity(AnimalSearchDTO animalDTO)
    {
        return new AnimalEntity(animalDTO.getId(),animalDTO.getName(),animalDTO.getAge(), animalDTO.getRace(),
                animalDTO.getDescription());
    }

    public AnimalSearchDTO mapAnimalEntityToAnimalDTO(AnimalEntity animalEntity)
    {
        return AnimalSearchDTO.builder().id(animalEntity.getId()).description(animalEntity.getDescription())
                .age(animalEntity.getAge()).race(animalEntity.getRace())
                .name(animalEntity.getName()).build();
    }
}
