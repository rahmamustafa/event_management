package gov.iti.evento.repositories;

import gov.iti.evento.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailIgnoreCaseAndPassword(@NonNull String email, @NonNull String password);
    Optional<User> findByEmailIgnoreCase(@NonNull String email);   
    Optional<User> findByEmail( String email);
    Optional<User> findByEmailIgnoreCaseAndPassword(@NonNull String email , @NonNull String password);
    boolean existsByEmailIgnoreCase(@NonNull String email);
    @Query("SELECT COUNT(e) FROM User e WHERE e.birthDate BETWEEN ?1 AND ?2")
    long countByBirthDateBetween(LocalDate startDate, LocalDate endDate);
}