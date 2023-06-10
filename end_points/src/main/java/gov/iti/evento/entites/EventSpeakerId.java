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
public class EventSpeakerId implements Serializable {
    private static final long serialVersionUID = -7554274552546255844L;
    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "speaker_id", nullable = false)
    private Integer speakerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventSpeakerId entity = (EventSpeakerId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.speakerId, entity.speakerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, speakerId);
    }

}