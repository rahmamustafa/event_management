package gov.iti.evento.repositories;

import gov.iti.evento.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Integer> {
    public List<Event> findByCategoryType(String categoryType);
    public List<Event> findByStatus(String status);
}
