package gov.iti.evento.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.evento.entites.Category}
 */
@Value
@Data
@AllArgsConstructor
public class CategoryDto implements Serializable {
    String type;
}