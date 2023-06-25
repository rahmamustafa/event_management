package gov.iti.evento.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "event_review")
public class EventReview implements Serializable {
    @EmbeddedId
    private EventReviewId id;

    @MapsId("eventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 45)
    @Column(name = "review", length = 45)
    private String review;

    @NotNull
    @Column(name = "review_date", nullable = false)
    private Timestamp reviewDate;

}