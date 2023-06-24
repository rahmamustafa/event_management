package gov.iti.evento.services.mappers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import gov.iti.evento.services.speaker.SpeakerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.dtos.SpeakerMostSpeakingDto;
import gov.iti.evento.services.dtos.SpeakersDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface SpeakerMapper {
     SpeakerMapper INSTANCE = Mappers.getMapper( SpeakerMapper.class );
 
    Speaker toEntity(SpeakerMostSpeakingDto speakingDto);

    @Mapping(source = "image", target = "image", qualifiedByName = "toPath")
    SpeakerMostSpeakingDto toDto(Speaker speaker);
    SpeakerDto toSpeakerDto(Speaker speaker);

    @Mapping(source = "image", target = "image", qualifiedByName = "toPath")
    SpeakersDto toSpeakersDto(Speaker speaker);
    
    @Named("toPath")
    default String toPath(String image) {
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
        Path imagePath = Paths.get (UPLOAD_DIRECTORY, image);
         System.out.println("path speaker ->"+imagePath);
        boolean fileExists = Files.exists(imagePath);
        if(fileExists){
            byte[] imageBytes;
            try {
                imageBytes = Files.readAllBytes(imagePath);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                System.out.println("path speaker ->"+imagePath);
                return base64Image;
            } catch (IOException e) {
                return "";
            }
        }
        return  "";
    }
}
