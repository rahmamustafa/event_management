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
public class AvailableTicketId implements Serializable {
    private static final long serialVersionUID = 6699399965689088184L;
    @NotNull
    @Column(name = "ticket_id", nullable = false)
    private Integer ticketId;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AvailableTicketId entity = (AvailableTicketId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.ticketId, entity.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, ticketId);
    }

}