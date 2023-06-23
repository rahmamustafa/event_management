package gov.iti.evento.services.mappers;


import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.RecommendationDto;
import gov.iti.evento.services.util.converters.ImageConverter;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface RecommendationMapper {

    RecommendationMapper INSTANCE= Mappers.getMapper(RecommendationMapper.class);
    @Mappings({


            @Mapping(source = "title", target = "title"),
            @Mapping(source = "location", target = "location"),
            @Mapping(target = "image", expression = "java(recoverImageFromUrl(event.getImage()))"),
            @Mapping(source = "category.type", target = "categoryType")
            })
    RecommendationDto toDto(Event event);

    default byte[] recoverImageFromUrl(String urlText) throws Exception {
        return ImageConverter.recoverImageFromUrl(urlText);
    }
}
