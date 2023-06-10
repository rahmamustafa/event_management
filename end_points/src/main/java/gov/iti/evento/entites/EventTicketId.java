package gov.iti.evento.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class EventTicketId implements Serializable {
    private static final long serialVersionUID = 7587029791674106176L;
    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "ticket_id", nullable = false)
    private Integer ticketId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventTicketId entity = (EventTicketId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.ticketId, entity.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, ticketId);
    }

}