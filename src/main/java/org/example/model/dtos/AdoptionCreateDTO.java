package org.example.model.dtos;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AdoptionCreateDTO
{

    private long id;

    @NotNull
    @NotBlank(message = "animalId can not be blank")
    @Min(value = 0, message = "animalId must not be negative")
    private long animalId;

    @NotNull
    @NotBlank(message = "userId can not be blank")
    @Min(value = 0, message = "userId must not be negative")
    private long userId;
}
