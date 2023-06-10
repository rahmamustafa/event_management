package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.EventSpeakerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, EventSpeakerId> {
}