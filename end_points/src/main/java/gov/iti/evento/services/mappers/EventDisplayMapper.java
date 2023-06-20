package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Event;

import gov.iti.evento.services.dtos.EventoDetailesDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component

public interface EventDisplayMapper{

    @Mapping(source = "title", target= "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source="isOnline",target = "isOnline")
    @Mapping(source = "eventDate", target = "eventDate")
    @Mapping(source= "location", target="location")
//    @Mapping(source="location", target="location")
    EventoDetailesDTO eventToEventDetailsDTO (Event event);
    EventoDetailesDTO toDto(Event event);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event partialUpdate(EventoDetailesDTO eventoDetailesDTO, @MappingTarget Event event);
}
