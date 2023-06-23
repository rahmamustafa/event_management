package gov.iti.evento.services.dtos;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
@Value
public class RecommendationDto implements Serializable {

    private Integer id;
    private String title;
    private String location;
    private String categoryType;
    byte[] image;


}
