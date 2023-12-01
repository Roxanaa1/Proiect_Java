package org.example.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.dtos.AnimalCreateDTO;
import org.example.model.dtos.AnimalSearchDTO;
import org.example.model.entities.AnimalEntity;
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
    public AnimalSearchDTO createAnimal(AnimalSearchDTO animalToCreateDTO)
    {

        AnimalEntity animalEntity= animalMapper.mapAnimalDTOtoAnimalEntity(animalToCreateDTO);
        AnimalEntity createdAnimalEntity=animalRepository.save(animalEntity);

        return animalMapper.mapAnimalEntityToAnimalDTO(createdAnimalEntity);

    }
    public List<AnimalSearchDTO> findAnimalsById(Long id)
    {
        if(id==null || id<=0)
        {
            throw new IllegalArgumentException("Id-ul trebuie sa fie un numar pozitiv nenul");
        }
        return animalRepository.findById( id).stream()
                .map(entity->animalMapper.mapAnimalEntityToAnimalDTO(entity))
                .collect(Collectors.toList());
    }

    @Transactional
    public AnimalCreateDTO updateAnimal(Long id, AnimalCreateDTO animalCreateDTO)
    {
        if(id==null || id<=0)
        {
            throw new IllegalArgumentException("Id-ul trebuie sa fie un numar pozitiv nenul");
        }

        try {
            // Verifică dacă animalul există în baza de date
            AnimalEntity existingAnimal = animalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));

            // Verifică nulitatea înainte de mapare
            if (existingAnimal != null)
            {
                modelMapper.map(animalCreateDTO, existingAnimal);
                // Salvează entitatea actualizată în baza de date
                AnimalEntity updatedAnimal = animalRepository.save(existingAnimal);

                return modelMapper.map(updatedAnimal, AnimalCreateDTO.class);
            } else
            {
                return null;
            }
        } catch (RuntimeException e)
        {
            System.out.println("A apărut o excepție: " + e.getMessage());
           return null;
        }
    }


    public void deleteAnimalById(Long id)
    {
        //validari daca exista animalul,etc
        if (id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id-ul trebuie să fie un număr pozitiv nenul.");
        }

        if (!animalRepository.existsById(id))
        {
            throw new EntityNotFoundException("Adopția cu id-ul " + id + " nu a fost găsită.");
        }
        animalRepository.deleteById(id);
    }
}
