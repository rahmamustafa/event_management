package gov.iti.evento.services.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpeakersDto implements Serializable {
    private Integer id;
    private String jobTitle;
    private String name;
    private String description;
    private String image;


}
