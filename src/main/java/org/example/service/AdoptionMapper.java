package org.example.service;
import org.example.model.dtos.*;
import org.example.model.entities.Adoption;
import org.example.model.entities.Adoption;
import org.example.model.entities.AnimalEntity;
import org.springframework.stereotype.Component;
@Component
public class AdoptionMapper
{
    public Adoption mapAdoptionCreateDTOToAdoptionEntity(AdoptionCreateDTO adoptionCreateDTO)
    {
        Adoption adoption = new Adoption();
        adoption.setAnimalId(adoptionCreateDTO.getAnimalId());
        adoption.setUserId(adoptionCreateDTO.getUserId());
        return adoption;
    }

    public AdoptionSearchDTO mapAdoptionEntityToAdoptionSearchDTO(Adoption adoption)
    {
        AdoptionSearchDTO adoptionSearchDTO = new AdoptionSearchDTO();
        adoptionSearchDTO.setId(adoption.getId());
        adoptionSearchDTO.setAnimalId(adoption.getAnimalId());
        adoptionSearchDTO.setUserId(adoption.getUserId());
        return adoptionSearchDTO;
    }
    public Adoption updateAdoptionFromDto(AdoptionUpdateDTO adoptionUpdateDTO, Adoption existingAdoption)
    {

        existingAdoption.setUserId(adoptionUpdateDTO.getUserId());
        existingAdoption.setAnimalId(adoptionUpdateDTO.getAnimalId());

        return existingAdoption;
    }

}
