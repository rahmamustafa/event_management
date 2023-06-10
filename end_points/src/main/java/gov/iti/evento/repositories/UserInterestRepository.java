package gov.iti.evento.repositories;

import gov.iti.evento.entites.UserInterest;
import gov.iti.evento.entites.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {
}