package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.EventoDetailesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EventDisplayMapper{

    @Mapping(source = "title", target= "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source="isOnline",target = "isOnline")
    @Mapping(source = "eventDate", target = "eventDate")
//    @Mapping(source="location", target="location")
    EventoDetailesDTO eventToEventDetailsDTO (Event event);
}
