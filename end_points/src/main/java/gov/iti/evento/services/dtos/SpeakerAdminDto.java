package gov.iti.evento.services.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpeakerAdminDto {
    private Integer id;
    private String jobTitle;
    private String name;
    private String description;
    private String image;
    private Integer age;
}
