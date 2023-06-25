package gov.iti.evento.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class EventReviewId implements Serializable {
    private static final long serialVersionUID = 6367236822978965770L;
    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventReviewId entity = (EventReviewId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, userId);
    }

}