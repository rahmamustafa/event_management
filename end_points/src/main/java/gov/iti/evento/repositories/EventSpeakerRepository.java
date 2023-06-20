package gov.iti.evento.repositories;

import gov.iti.evento.entites.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, EventSpeakerId> {
    List<EventSpeaker> findBySpeakerNameIgnoreCaseLike (String speaker);
    List<EventSpeaker> findByEventId(Integer eventId);
//    @Query("SELECT es.speaker FROM EventSpeaker es WHERE es.eventId = :eventId")
//    List<Speaker> findSpeakersByEventId(@Param("eventId") Integer eventId);
}