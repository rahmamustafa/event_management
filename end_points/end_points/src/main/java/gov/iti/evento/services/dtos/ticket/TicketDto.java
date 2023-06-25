package gov.iti.evento.services.dtos.ticket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.evento.entites.Ticket}
 */
@Value
public class TicketDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String type;
}