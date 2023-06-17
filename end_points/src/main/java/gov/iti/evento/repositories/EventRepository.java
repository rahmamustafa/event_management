package gov.iti.evento.repositories;

import gov.iti.evento.entites.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Integer> {
    public List<Event> findByCategoryType(String categoryType);
    public List<Event> findByStatus(String status);
    public Page<Event> findAll(@PageableDefault(size = 2)Pageable pageable);
}
