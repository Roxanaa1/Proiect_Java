package org.example.model.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class AnimalUpdateDTO
{

    private Long id;
    private String name;
    private int age;
    private String  race;
    private String description;
}
