package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
}