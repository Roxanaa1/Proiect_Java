package org.example.service;
import org.example.model.dtos.AnimalSearchDTO;
import org.example.model.entities.AnimalEntity;
import org.springframework.stereotype.Component;
@Component
public class AnimalMapper
{
    public AnimalEntity mapAnimalDTOtoAnimalEntity(AnimalSearchDTO animalDTO)
    {
        AnimalEntity animalEntity=new AnimalEntity();
        animalEntity.setId(animalDTO.getId());
        animalEntity.setName(animalDTO.getName());
        animalEntity.setAge(animalDTO.getAge());
        animalEntity.setRace(animalDTO.getRace());
        animalEntity.setDescription(animalDTO.getDescription());

        return animalEntity;
    }

    public AnimalSearchDTO mapAnimalEntityToAnimalDTO(AnimalEntity animalEntity)
    {
        return AnimalSearchDTO.builder().id(animalEntity.getId()).description(animalEntity.getDescription())
                .age(animalEntity.getAge()).race(animalEntity.getRace())
                .name(animalEntity.getName()).build();
    }
}
