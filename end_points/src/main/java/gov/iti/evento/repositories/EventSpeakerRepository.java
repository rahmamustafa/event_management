package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.EventSpeakerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, EventSpeakerId> {
}