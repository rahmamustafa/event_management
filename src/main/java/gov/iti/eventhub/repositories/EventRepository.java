package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event,Integer> {
}
