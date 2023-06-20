package gov.iti.evento.repositories;

import gov.iti.evento.entites.Event;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query("SELECT e FROM Event e WHERE DATE(e.eventDate) = DATE(:date)")
    Page<Event> findByDate(@Param("date") LocalDate date, Pageable pageable);
    public List<Event> findByCategoryType(String categoryType);
}