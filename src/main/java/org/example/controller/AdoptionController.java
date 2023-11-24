package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.dtos.*;
import org.example.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CustomResponseDTO> makeAdoption(@RequestBody @Valid AdoptionCreateDTO adoptionCreateDTO, BindingResult bindingResult)
    {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        AdoptionSearchDTO adoptionSearchDTO = adoptionService.makeAdoption(adoptionCreateDTO);

        customResponseDTO.setResponseObject(adoptionSearchDTO);
        customResponseDTO.setResponseMessage("Adoption made successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }



}
