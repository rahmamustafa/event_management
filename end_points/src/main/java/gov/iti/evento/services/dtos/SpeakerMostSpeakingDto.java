package gov.iti.evento.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerMostSpeakingDto {
    private int id;
    private String name;
    private String jobTitle;
    private String image;

}
