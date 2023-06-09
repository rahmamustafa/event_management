package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.EventReview;
import gov.iti.eventhub.entites.EventReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReviewRepository extends JpaRepository<EventReview, EventReviewId> {
}