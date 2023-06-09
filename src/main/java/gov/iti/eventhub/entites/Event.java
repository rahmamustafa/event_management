package gov.iti.eventhub.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_date", nullable = false)
    private Instant eventDate;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "is_online", nullable = false)
    private Byte isOnline;

    @Column(name = "creation_date")
    private Instant creationDate;

}