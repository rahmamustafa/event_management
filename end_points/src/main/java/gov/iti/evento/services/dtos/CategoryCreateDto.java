package gov.iti.evento.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.evento.entites.Category}
 */
@Value
public class CategoryCreateDto implements Serializable {
    @Pattern(regexp = "\\w")
    @NotBlank(message = "Please Enter Type")
    String type;
}