package gov.iti.evento.services.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class SpeakersDto implements Serializable {
    private Integer id;
    private String jobTitle;
    private String name;
    private String description;
    private String image;


    public SpeakersDto (Integer speakerId, String speakerName, String jobTitle, String description){
        this.description =description;
        this.image =image;
        this.id =speakerId;
        this.jobTitle=jobTitle;
        this.name =speakerName;

    }
    public SpeakersDto(){}



}
