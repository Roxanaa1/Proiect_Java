package org.example.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.dtos.*;
import org.example.model.entities.Adoption;
import org.example.model.entities.User;
import org.example.repository.AdoptionRepository;
import org.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AdoptionService
{
    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Autowired
    AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper,
                    UserRepository userRepository, UserMapper userMapper,ModelMapper modelMapper)
    {
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.modelMapper=modelMapper;
    }

    public AdoptionSearchDTO createAdoption(AdoptionCreateDTO adoptionCreateDTO)
    {
        User user = userRepository.findById(adoptionCreateDTO.getUserId()).orElseThrow(() -> new RuntimeException());
        Adoption adoption = adoptionMapper.mapAdoptionCreateDTOToAdoptionEntity(adoptionCreateDTO);
        adoption.setUser(user);
        Adoption savedAdoption = adoptionRepository.save(adoption);
        UserSearchDTO userSearchDTO = userMapper.mapUserEntityToUserSearchDTO(savedAdoption.getUser());
        AdoptionSearchDTO adoptionSearchDTO = adoptionMapper.mapAdoptionEntityToAdoptionSearchDTO(savedAdoption);
        adoptionSearchDTO.setUser(userSearchDTO);

        return adoptionSearchDTO;
    }
    public List<AdoptionSearchDTO> findAdoptionById(Long id)
    {
        if (id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id-ul trebuie să fie un număr pozitiv nenul.");
        }

        return adoptionRepository.findById( id).stream()
                .map(entity->adoptionMapper.mapAdoptionEntityToAdoptionSearchDTO(entity))
                .collect(Collectors.toList());
    }
    @Transactional
    public AdoptionCreateDTO updateAdoption(Long id, AdoptionCreateDTO adoptionCreateDTO)
    {
        if (id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id-ul trebuie să fie un număr pozitiv nenul.");
        }
        Adoption existingAdoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoption not found with id " + id));

        modelMapper.map(adoptionCreateDTO, existingAdoption);
        Adoption updatedAdoption = adoptionRepository.save(existingAdoption);

        return modelMapper.map(updatedAdoption, AdoptionCreateDTO.class);
    }

    public void deleteAdoptionById(Long id)
    {
        if (id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id-ul trebuie să fie un număr pozitiv nenul.");
        }

        if (!adoptionRepository.existsById(id))
        {
            throw new EntityNotFoundException("Adopția cu id-ul " + id + " nu a fost găsită.");
        }

        adoptionRepository.deleteById(id);
    }

}
