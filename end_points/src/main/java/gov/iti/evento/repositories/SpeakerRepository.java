package gov.iti.evento.repositories;

import gov.iti.evento.entites.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
}