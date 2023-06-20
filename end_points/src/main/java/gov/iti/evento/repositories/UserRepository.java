package gov.iti.evento.repositories;

import gov.iti.evento.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailIgnoreCase(@NonNull String email);
}