package gov.iti.evento.repositories;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.EventSpeakerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, EventSpeakerId> {
    List<EventSpeaker> findBySpeakerNameIgnoreCaseLike (String speaker);
}