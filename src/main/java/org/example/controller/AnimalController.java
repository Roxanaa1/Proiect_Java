package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.dtos.AnimalDTO;
import org.example.model.dtos.CustomResponseDTO;
import org.example.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class AnimalController
{
    List<AnimalDTO> animalDTOlist = new ArrayList<>();
    private final AnimalService animalService;

    @Autowired
    AnimalController(AnimalService animalService)
    {
        this.animalService = animalService;
    }

    @GetMapping("/getAnimalsById/{id}")
    public List<AnimalDTO> getAnimalById(@PathVariable Long id)
    {
        return animalService.findAnimalsById(id);
    }

    @PostMapping(path = "/user")//POST-creaza alt animal
    public ResponseEntity<CustomResponseDTO> createNewAnimal(
            @RequestBody
            @Valid
            AnimalDTO animalDTO,
            BindingResult bindingResult)
    {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (bindingResult.hasErrors())
        {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        animalService.createAnimal(animalDTO);
        customResponseDTO.setResponseObject(animalDTO);
        animalDTOlist.add(animalDTO);
        customResponseDTO.setResponseMessage("User created successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }


    @PutMapping(path = "/animal/{id}")//PUT-actualizeaza
    public ResponseEntity<CustomResponseDTO> updateAnimal(
            @PathVariable Long id,
            @RequestBody @Valid AnimalDTO animalDTO,
            BindingResult bindingResult)
    {

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        if (bindingResult.hasErrors())
        {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        animalService.updateAnimal(id, animalDTO);
        customResponseDTO.setResponseObject(animalDTO);
        customResponseDTO.setResponseMessage("Animal updated successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping(path = "/animal/{id}")
    public ResponseEntity<CustomResponseDTO> deleteAnimal(@PathVariable Long id)
    {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if (animalService.deleteAnimal(id))
        {
            customResponseDTO.setResponseMessage("Animal deleted successfully");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
        } else
        {
            customResponseDTO.setResponseMessage("Animal not found");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

}
