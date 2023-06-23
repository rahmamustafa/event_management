package gov.iti.evento.repositories;

import gov.iti.evento.entites.*;
import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.EventSpeakerId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;


@Repository
public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, EventSpeakerId> {
//    List<EventSpeaker> findBySpeakerNameIgnoreCaseLike (String speaker);
    List<EventSpeaker> findByEvent(Event event);
//    @Query("SELECT es.speaker FROM EventSpeaker es WHERE es.eventId = :eventId")
//    List<Speaker> findSpeakersByEventId(@Param("eventId") Integer eventId);
    List<EventSpeaker> findBySpeakerNameIgnoreCaseLike(String speaker, Pageable pageable);
}