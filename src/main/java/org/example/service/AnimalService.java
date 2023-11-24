package org.example.service;

import org.example.model.dtos.AnimalSearchDTO;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.AnimalEntity;
import org.example.model.entities.UserEntity;
import org.example.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService
{
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Autowired
    AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper)
    {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

//    public List<AnimalDTO> findAnimalsById(Long id)
//    {
//        return animalRepository.findById(id);
//    }


    public AnimalSearchDTO createAnimal(AnimalSearchDTO animalToCreateDTO)
    {

            AnimalEntity animalEntity= animalMapper.mapAnimalDTOtoAnimalEntity(animalToCreateDTO);
            AnimalEntity createdAnimalEntity=animalRepository.save(animalEntity);

            return animalMapper.mapAnimalEntityToAnimalDTO(createdAnimalEntity);

    }


//    public AnimalDTO updateAnimal(Long id, AnimalDTO animalDTO) {
//        AnimalEntity animalEntity = new AnimalEntity();
//        // Setează proprietățile lui AnimalEntity în funcție de AnimalDTO...
//        animalEntity.setId(id);
//        // ...alte setări...
//
//        animalRepository.save(animalEntity);
//        return animalDTO; // sau returnează un AnimalDTO transformat din AnimalEntity actualizat
//    }


//    public boolean deleteAnimal(Long id)
//    {
//        return animalRepository.deleteById(id);
//    }
}
