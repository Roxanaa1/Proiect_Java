package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.dtos.*;
import org.example.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1")
public class AdoptionController
{
    private final AdoptionService adoptionService;

    @Autowired
    AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }


    @PostMapping(path = "/adoption")
    public ResponseEntity<CustomResponseDTO> createAdoption(@RequestBody @Valid
                         AdoptionCreateDTO adoptionCreateDTO, BindingResult bindingResult)
    {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        AdoptionSearchDTO adoptionSearchDTO = adoptionService.createAdoption(adoptionCreateDTO);

        customResponseDTO.setResponseObject(adoptionSearchDTO);
        customResponseDTO.setResponseMessage("Adoption made successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/adoption/getAdoptionsById/{id}")
    public ResponseEntity<CustomResponseDTO> getAdoptionById(@PathVariable Long id)
    { // -> Path param ../23 (23 fiind id-ul)

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        List<AdoptionSearchDTO> foundAdoptions = adoptionService.findAdoptionById(id);
        if(Objects.isNull(foundAdoptions)|| foundAdoptions.isEmpty())
        {
            customResponseDTO.setResponseMessage("No adoption was found by this id.");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
        customResponseDTO.setResponseObject(foundAdoptions);
        customResponseDTO.setResponseMessage("Adoption found successfully!");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }
    @PutMapping(path = "/adoption/{id}")
    public ResponseEntity<CustomResponseDTO> updateAdoption(
            @PathVariable Long id,
            @RequestBody @Valid AdoptionCreateDTO adoptionCreateDTO,
            BindingResult bindingResult) {

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        AdoptionCreateDTO updatedDTO = adoptionService.updateAdoption(id, adoptionCreateDTO);
        customResponseDTO.setResponseObject(updatedDTO);
        customResponseDTO.setResponseMessage("Adoption updated successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping(path = "/adoption/{adoptionId}")
    public ResponseEntity deleteAdoption(@PathVariable Long adoptionId)
    {
        adoptionService.deleteAdoptionById(adoptionId);
        return new ResponseEntity("Adoption deleted", HttpStatus.OK);
    }

}
