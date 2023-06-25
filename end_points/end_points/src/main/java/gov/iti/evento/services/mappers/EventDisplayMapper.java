package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Event;

import gov.iti.evento.services.dtos.EventoDetailesDTO;
import gov.iti.evento.services.util.converters.ImageConverter;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component


public interface EventDisplayMapper{


    @Mappings({
            @Mapping(target = "image", expression = "java(recoverImageFromUrl(event.getImage()))"),


    })
    EventoDetailesDTO eventToEventDetailsDTO (Event event)throws Exception;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event partialUpdate(EventoDetailesDTO eventoDetailesDTO, @MappingTarget Event event);
    default String mapByteArrayToString(byte[] bytes) {
        return null;
    }
    default byte[] recoverImageFromUrl(String urlText) throws Exception {
        return ImageConverter.recoverImageFromUrl(urlText);
    }
}
