package org.example.model.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnimalCreateDTO
{
    private Long id;
    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 255, message = "Name length must be between 2 and 255 characters")
    private String name;
    @Min(value = 0, message = "Age must be a positive number")
    private int age;
    @NotBlank(message = "Race must not be blank")
    @Size(min = 2, max = 255, message = "Race length must be between 2 and 255 characters")
    private String  race;
    @NotBlank(message = "Description must not be blank")
    @Size(min = 20, max = 1000, message = "Description length must be between 2 and 1000 characters")
    private String description;

}
