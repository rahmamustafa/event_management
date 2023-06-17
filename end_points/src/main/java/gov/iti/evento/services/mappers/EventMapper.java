package gov.iti.evento.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.EventByDateDto;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @Mappings({
        @Mapping(target = "category", source = "category.type"),
        @Mapping(target = "time", source = "eventDate"),
        @Mapping(target = "online", source = "isOnline",qualifiedByName = "byteToBoolean")
    })
    EventByDateDto eventToeventByDateDto(Event event);

    default Page<EventByDateDto> map(Page<Event> eventPage) {
            return eventPage.map(this::eventToeventByDateDto);
        }
    @Named(value = "byteToBoolean")    
    default boolean byteToBoolean(byte value) {
        return value != 0;
    }
}
