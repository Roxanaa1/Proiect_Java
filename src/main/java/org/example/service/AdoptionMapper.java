package org.example.service;

import org.example.model.dtos.AdoptionCreateDTO;
import org.example.model.dtos.AdoptionSearchDTO;
import org.example.model.entities.Adoption;

public class AdoptionMapper
{
    public Adoption mapAdoptionCreateDTOToAdoptionEntity(AdoptionCreateDTO adoptionCreateDTO){
        Adoption adoption = new Adoption();
        adoption.setAnimalId(adoptionCreateDTO.getAnimalId());
        adoption.setUserId(adoptionCreateDTO.getUserId());
        return adoption;
    }

    public AdoptionSearchDTO mapAdoptionEntityToAdoptionSearchDTO(Adoption adoption){
        AdoptionSearchDTO adoptionSearchDTO = new AdoptionSearchDTO();
        adoptionSearchDTO.setId(adoption.getId());
        adoptionSearchDTO.setAnimalId(adoption.getAnimalId());
        adoptionSearchDTO.setUserId(adoption.getUserId());
        return adoptionSearchDTO;
    }
}
