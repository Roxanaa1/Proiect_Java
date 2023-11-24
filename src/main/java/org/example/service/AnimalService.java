package org.example.service;
import jakarta.transaction.Transactional;
import org.example.model.dtos.AnimalCreateDTO;
import org.example.model.dtos.AnimalSearchDTO;
import org.example.model.dtos.UserCreateDTO;
import org.example.model.dtos.UserSearchDTO;
import org.example.model.entities.AnimalEntity;
import org.example.model.entities.User;
import org.example.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService
{
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final ModelMapper modelMapper;

    @Autowired
    AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper,ModelMapper modelMapper)
    {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
        this.modelMapper=modelMapper;
    }
    public List<AnimalSearchDTO> findAnimalsById(Long id)
    {
        // validate, transform...
        return animalRepository.findById( id).stream()
                .map(entity->animalMapper.mapAnimalEntityToAnimalDTO(entity))
                .collect(Collectors.toList());
    }
    public AnimalSearchDTO createAnimal(AnimalSearchDTO animalToCreateDTO)
    {

            AnimalEntity animalEntity= animalMapper.mapAnimalDTOtoAnimalEntity(animalToCreateDTO);
            AnimalEntity createdAnimalEntity=animalRepository.save(animalEntity);

            return animalMapper.mapAnimalEntityToAnimalDTO(createdAnimalEntity);

    }

    @Transactional
    public AnimalCreateDTO updateAnimal(Long id, AnimalCreateDTO animalCreateDTO)
    {
        // Verifică dacă utilizatorul există în baza de date
        AnimalEntity existingAnimal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));

        modelMapper.map(animalCreateDTO, existingAnimal);

        // Salvează entitatea actualizată în baza de date
        AnimalEntity updatedAnimal = animalRepository.save(existingAnimal);

        return modelMapper.map(updatedAnimal, AnimalCreateDTO.class);
    }


    public void deleteAnimalById(Long id)
    {
        //validari ex daca exista userul
        animalRepository.deleteById(id);
    }
}
