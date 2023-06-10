package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventReview;
import gov.iti.evento.entites.EventReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReviewRepository extends JpaRepository<EventReview, EventReviewId> {
}