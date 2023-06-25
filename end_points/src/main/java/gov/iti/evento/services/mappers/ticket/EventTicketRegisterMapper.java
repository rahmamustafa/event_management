package gov.iti.evento.services.mappers.ticket;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.services.dtos.ticket.EventTicketRegisterDto;
import gov.iti.evento.services.mappers.ticket.TicketMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TicketMapper.class})
public interface EventTicketRegisterMapper {
//    @Mapping(source = "eventTicketIdTicketId", target = "eventTicketId.ticketId")
//    @Mapping(source = "eventTicketIdEventId", target = "eventTicketId.eventId")
    EventTicket toEntity(EventTicketRegisterDto eventTicketDto);

    @InheritInverseConfiguration(name = "toEntity")
    EventTicketRegisterDto toDto(EventTicket eventTicket);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventTicket partialUpdate(EventTicketRegisterDto eventTicketDto, @MappingTarget EventTicket eventTicket);
}