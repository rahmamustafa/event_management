package gov.iti.evento.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_speaker")
public class EventSpeaker implements Serializable {

    public EventSpeaker(Event event, Speaker speaker){
        this.event=event;
        this.speaker=speaker;
    }
    @EmbeddedId
    private EventSpeakerId id = new EventSpeakerId();

    @MapsId("eventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @MapsId("speakerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "speaker_id", nullable = false)
    private Speaker speaker;

}
