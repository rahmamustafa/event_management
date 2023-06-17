package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.CategoryCreateDto;
import gov.iti.evento.services.dtos.EventDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper( EventMapper.class );
    @Mapping(source = "categoryType", target = "category.type")
    Event toEntity(EventDto eventDto);

    @Mapping(source = "category.type", target = "categoryType")
    EventDto toDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryType", target = "category.type")
    Event partialUpdate(EventDto eventDto, @MappingTarget Event event);

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
