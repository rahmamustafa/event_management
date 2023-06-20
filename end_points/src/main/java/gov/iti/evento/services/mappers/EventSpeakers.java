package gov.iti.evento.services.mappers;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.dtos.EventSpeakersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component

public interface EventSpeakers {
    @Mapping(source = "jobTitle", target= "jobTitle")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source="name",target = "name")
    @Mapping(source = "id", target="id")
    EventSpeakersDto eventToEventDetailsDTO (Speaker speaker);
}


