package org.example.model.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomResponseDTO {

        //    @JsonIgnore
        private Object responseObject;
        private String responseMessage;
}