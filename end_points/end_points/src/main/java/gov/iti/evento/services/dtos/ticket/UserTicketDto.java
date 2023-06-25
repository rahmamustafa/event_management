package gov.iti.evento.services.dtos.ticket;

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
    private Integer userId;
//    private EventTicketRegisterDto eventTicket;
    private Integer quantity;

}