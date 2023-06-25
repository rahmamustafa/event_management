package gov.iti.evento.services.mappers;


import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.dtos.SpeakerAdminDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component

public interface SpeakerAdminMapper {

    SpeakerAdminMapper INSTANCE = Mappers.getMapper( SpeakerAdminMapper.class );


    @Mappings({
            @Mapping(source = "jobTitle", target = "jobTitle"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "image", target = "image", qualifiedByName = "toPath"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "id", target = "id"),
    })
    SpeakerAdminDto toDto (Speaker speaker) throws Exception;

    @Named("toPath")
    default String toPath(String image) {
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
        Path imagePath = Paths.get(UPLOAD_DIRECTORY, image);
        System.out.println("path event ->" + imagePath);
        boolean fileExists = Files.exists(imagePath);
        if (fileExists) {
            byte[] imageBytes;
            try {
                imageBytes = Files.readAllBytes(imagePath);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                System.out.println("path event ->" + imagePath);
                return base64Image;
            } catch (IOException e) {
                return "";
            }
        }
        return  "";
    }
}