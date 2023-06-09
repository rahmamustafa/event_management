package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}