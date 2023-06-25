package gov.iti.evento.services.dtos;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.evento.entites.UserTicket}
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTicketDto implements Serializable {
    Integer userId;

    Integer ticketId;
    Integer eventId;
    int quantity;
}