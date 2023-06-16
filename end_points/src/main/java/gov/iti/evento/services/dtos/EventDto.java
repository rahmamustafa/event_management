package gov.iti.evento.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EventDto implements Serializable {

    private String title;
    private String image;
    private String description;
    private String categoryType;

}
