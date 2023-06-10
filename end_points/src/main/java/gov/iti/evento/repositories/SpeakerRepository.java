package gov.iti.evento.repositories;

import gov.iti.evento.entites.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
}