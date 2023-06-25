package gov.iti.evento.services.mappers;


import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.RecommendationDto;
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
public interface RecommendationMapper {

    RecommendationMapper INSTANCE= Mappers.getMapper(RecommendationMapper.class);
    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "image", target = "image", qualifiedByName = "toPath"),
            @Mapping(source = "category.type", target = "categoryType")
    })
    RecommendationDto toDto(Event event) throws Exception;

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