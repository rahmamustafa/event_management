package gov.iti.evento.services.mappers;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.util.converters.ImageConverter;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component

public interface EventSpeakersMapper {
    EventSpeakersMapper INSTANCE = Mappers.getMapper(EventSpeakersMapper.class);

    @Mappings({

    @Mapping(source = "jobTitle", target= "jobTitle"),
    @Mapping(source = "description", target = "description"),
            @Mapping(target = "image", expression = "java(recoverImageFromUrl(speaker.getImage()))"),
    @Mapping(source="name",target = "name"),
    @Mapping(source = "id", target="id")})
    SpeakersDto toDto (Speaker speaker)throws Exception;


    default byte[] recoverImageFromUrl(String urlText) throws Exception {
        return ImageConverter.recoverImageFromUrl(urlText);
    }
}


