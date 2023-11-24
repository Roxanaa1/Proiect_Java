package org.example.service;

import org.example.model.dtos.*;
import org.example.model.entities.Adoption;
import org.example.model.entities.User;
import org.example.repository.AdoptionRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService
{
    //adopt animal
    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper,
                    UserRepository userRepository, UserMapper userMapper) {
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public AdoptionSearchDTO makeAdoption(AdoptionCreateDTO adoptionCreateDTO) {
        //validam userul, daca exista un user cu id-ul = user_id din request
        User user = userRepository.findById(adoptionCreateDTO.getUserId()).orElseThrow(() -> new RuntimeException());

        //construim un adoption entity pe baza adoptionCreateDTO (requestul de la client)
        Adoption adoption = adoptionMapper.mapAdoptionCreateDTOToAdoptionEntity(adoptionCreateDTO);
        adoption.setUser(user);
        //salvam adoptia
        Adoption savedAdoption = adoptionRepository.save(adoption);

        //construim un response object (UserSearchDTO) pe care il returnam catre controller
        UserSearchDTO userSearchDTO = userMapper.mapUserEntityToUserSearchDTO(savedAdoption.getUser());
        AdoptionSearchDTO adoptionSearchDTO = adoptionMapper.mapAdoptionEntityToAdoptionSearchDTO(savedAdoption);
        adoptionSearchDTO.setUser(userSearchDTO);
        return adoptionSearchDTO;
    }


}
