package gov.iti.evento.services.mappers;
import gov.iti.evento.entites.EventTicketId;
import gov.iti.evento.entites.UserTicket;
import gov.iti.evento.services.dtos.UserTicketDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserTicketMapper {
    @Mapping(source = "ticketId", target = "eventTicket.eventTicketId.ticketId")
    @Mapping(source = "eventId", target = "eventTicket.eventTicketId.eventId")
    @Mapping(source = "userId", target = "user.id")
    UserTicket toEntity(UserTicketDto userTicketDto);

    @InheritInverseConfiguration(name = "toEntity")
    UserTicketDto toDto(UserTicket userTicket);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserTicket partialUpdate(UserTicketDto userTicketDto, @MappingTarget UserTicket userTicket);
}