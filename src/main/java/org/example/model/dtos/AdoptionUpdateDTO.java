package org.example.model.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class AdoptionUpdateDTO
{
    private long id;

    @NotNull
    @Min(value = 0, message = "animalId must not be negative")
    private long animalId;

    @NotNull
    @Min(value = 0, message = "userId must not be negative")
    private long userId;
}