package gov.iti.evento.repositories;

import gov.iti.evento.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event,Integer> {
   
}
