package org.example.controller;
import jakarta.validation.Valid;
import org.example.model.dtos.*;
import org.example.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1")
public class AnimalController
{
    List<AnimalSearchDTO> animalDTOlist = new ArrayList<>();
    private final AnimalService animalService;

    @Autowired
    AnimalController(AnimalService animalService)
    {
        this.animalService = animalService;
    }
    @GetMapping("/animal/getAnimalsById/{id}")
    public ResponseEntity<CustomResponseDTO> getAnimalsById(@PathVariable Long id)
    { // -> Path param ../23 (23 fiind id-ul)

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        List<AnimalSearchDTO> foundUsers = animalService.findAnimalsById(id);
        if(Objects.isNull(foundUsers)|| foundUsers.isEmpty())
        {
            customResponseDTO.setResponseMessage("No animal was found by this id.");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
        customResponseDTO.setResponseObject(foundUsers);
        customResponseDTO.setResponseMessage("Animals found successfully!");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }
    @PostMapping(path = "/animal")//POST-creaza alt animal
    public ResponseEntity<CustomResponseDTO> createAnimal(
            @RequestBody
            @Valid
            AnimalSearchDTO animalDTO,
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

        AnimalSearchDTO animalSearchDTO= animalService.createAnimal(animalDTO);

        customResponseDTO.setResponseObject(animalDTO);
        customResponseDTO.setResponseMessage("User created successfully");
        return new ResponseEntity<>(customResponseDTO,HttpStatus.CREATED);
    }

    @PutMapping(path = "/animal/{id}")
    public ResponseEntity<CustomResponseDTO> updateAnimal(
            @PathVariable Long id,
            @RequestBody @Valid AnimalCreateDTO animalCreateDTO,
            BindingResult bindingResult) {

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        AnimalCreateDTO updatedDTO = animalService.updateAnimal(id, animalCreateDTO);
        customResponseDTO.setResponseObject(updatedDTO);
        customResponseDTO.setResponseMessage("Animal updated successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping(path = "/animal/{animalId}")
    public ResponseEntity deleteAnimal(@PathVariable Long animalId)
    {
        animalService.deleteAnimalById(animalId);
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }

}
