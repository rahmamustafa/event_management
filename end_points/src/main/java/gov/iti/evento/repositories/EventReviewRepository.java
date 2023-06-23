package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventReview;
import gov.iti.evento.entites.EventReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventReviewRepository extends JpaRepository<EventReview, EventReviewId> {
    public List<EventReview> getEventReviewByEvent_Id(int id);
    Optional<EventReview> findByEventIdAndUserId( Integer userId,Integer eventId);

}