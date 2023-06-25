package gov.iti.evento.repositories;

import gov.iti.evento.entites.Speaker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
    @Query("SELECT s, COUNT(es) AS eventCount FROM EventSpeaker es JOIN es.speaker s GROUP BY es.speaker ORDER BY eventCount DESC")
    List<Speaker> findTop8ByEventCount();
}