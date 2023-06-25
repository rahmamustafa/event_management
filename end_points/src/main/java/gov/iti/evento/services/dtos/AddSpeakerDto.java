package gov.iti.evento.services.dtos;

import gov.iti.evento.services.util.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddSpeakerDto implements Serializable {
    private  String jobTitle;
    private String description;
    private String name;
    private Integer age;
    private String image;



}
