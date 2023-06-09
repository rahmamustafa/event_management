package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.EventSpeaker;
import gov.iti.eventhub.entites.EventSpeakerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, EventSpeakerId> {
}