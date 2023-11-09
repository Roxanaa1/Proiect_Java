package org.example.repository;

import org.example.model.dtos.AnimalDTO;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.AnimalEntity;
import org.example.model.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AnimalRepository
{
    private AnimalDTO animalDTO1=new AnimalDTO(1L,"Pufu",2,"Ciobanesc","Caine,este cuminte si destept");
    private AnimalDTO animalDTO2=new AnimalDTO(2L,"Fetita",1,"Amstaff","Caine,este cuminte");
    private AnimalDTO animalDTO3=new AnimalDTO(3L,"Maya",3,"Ciobanesc german","Caine,este destept");


    private List<AnimalEntity> animalEntities = new ArrayList<>();
    public List<AnimalDTO> findById(Long id)
    {
        return Stream.of(animalDTO1, animalDTO2, animalDTO3)
                .filter(animalDTO -> animalDTO.getId() == id)
                .toList();
    }

    public AnimalEntity createAnimal(AnimalEntity animalToSaveIdDB)
    {
        animalEntities.add(animalToSaveIdDB);
        return animalToSaveIdDB;
    }


    public AnimalEntity save(AnimalEntity animal)
    {

        animalEntities.add(animal);
        return animal;
    }



    public boolean deleteById(Long id)
    {
        return animalEntities.removeIf(u -> u.getId().equals(id));
    }

}
