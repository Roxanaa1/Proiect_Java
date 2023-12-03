package org.example.service;
import org.example.model.dtos.AnimalSearchDTO;
import org.example.model.dtos.AnimalUpdateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.model.dtos.UserUpdateDTO;
import org.example.model.entities.AnimalEntity;
import org.example.model.entities.User;
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
    public AnimalEntity updateAnimalFromDto(AnimalUpdateDTO animalUpdateDTO, AnimalEntity existingAnimal)
    {
        existingAnimal.setName(animalUpdateDTO.getName());
        existingAnimal.setAge(animalUpdateDTO.getAge());
        existingAnimal.setRace(animalUpdateDTO.getRace());
        existingAnimal.setDescription(animalUpdateDTO.getDescription());

        return existingAnimal;
    }

    public AnimalSearchDTO mapAnimalEntityToAnimalDTO(AnimalEntity animalEntity)
    {
        return AnimalSearchDTO.builder().id(animalEntity.getId()).description(animalEntity.getDescription())
                .age(animalEntity.getAge()).race(animalEntity.getRace())
                .name(animalEntity.getName()).build();
    }
    public AnimalSearchDTO mapAnimalEntityToAnimalSearchDTO(AnimalEntity animalEntity){
        return AnimalSearchDTO.builder().id(animalEntity.getId()).age(animalEntity.getAge()).name(animalEntity.getName())
                .race(animalEntity.getRace()).description(animalEntity.getDescription())
                .build();
    }
}
