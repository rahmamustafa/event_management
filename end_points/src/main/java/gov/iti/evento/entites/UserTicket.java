package gov.iti.evento.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.annotations.SecondaryRow;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_ticket")
@NoArgsConstructor
public class UserTicket implements Serializable {
    @EmbeddedId
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public UserTicket(User user, EventTicket eventTicket, int quantity) {
        this.user = user;
        this.eventTicket = eventTicket;
        this.quantity = quantity;
    }
}