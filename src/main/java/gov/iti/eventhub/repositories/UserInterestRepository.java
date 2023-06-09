package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.UserInterest;
import gov.iti.eventhub.entites.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {
}