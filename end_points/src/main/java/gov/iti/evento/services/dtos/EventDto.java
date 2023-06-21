package gov.iti.evento.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;


@Value
public class EventDto implements Serializable {

    private Integer id;
    private String title;
    byte[] image;
    private String description;
    private String categoryType;

}
