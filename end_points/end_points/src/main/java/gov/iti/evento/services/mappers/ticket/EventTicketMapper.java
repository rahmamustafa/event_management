package gov.iti.evento.services.mappers.ticket;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.services.dtos.ticket.EventTicketDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TicketMapper.class})
@Component
public interface EventTicketMapper {
    EventTicket toEntity(EventTicketDto eventTicketDto);

    EventTicketDto toDto(EventTicket eventTicket);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventTicket partialUpdate(EventTicketDto eventTicketDto, @MappingTarget EventTicket eventTicket);
}