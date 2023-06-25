package gov.iti.evento.repositories;

import gov.iti.evento.entites.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    // Additional custom query methods if needed
}