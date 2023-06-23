package gov.iti.evento.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.SecondaryRow;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_ticket")
public class UserTicket implements Serializable {
    @EmbeddedId
    private UserTicketId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_ticket_id", nullable = false, referencedColumnName = "id")
    private EventTicket eventTicket;

    @Column(name = "quantity")
    private int quantity;

}