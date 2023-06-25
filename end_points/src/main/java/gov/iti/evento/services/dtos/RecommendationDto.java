package gov.iti.evento.services.dtos;

import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
public class RecommendationDto implements Serializable {

    private Integer id;
    private String title;
    private String location;
    private String categoryType;
    private String image;


    public RecommendationDto() {
    }
}