package org.example.model.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AdoptionSearchDTO
{
    private long id;
    private long animalId;
    private long userId;
    private UserSearchDTO user;
}
