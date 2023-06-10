package gov.iti.evento.repositories;

import gov.iti.evento.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}