package gov.iti.evento.services.mappers;


import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.EventCalenderDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface EventCalenderMapper {

    EventCalenderMapper INSTANCE = Mappers.getMapper(EventCalenderMapper.class);

    @Mappings({
            @Mapping(source = "id", target ="id")
    })
    public EventCalenderDto toDto (Event event) throws Exception;

}