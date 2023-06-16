package gov.iti.evento.repositories;

import gov.iti.evento.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
}
