package gov.iti.evento.services.dtos.eventReviews;

import gov.iti.evento.services.dtos.user.UserReviewDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * DTO for {@link gov.iti.evento.entites.EventReview}
 */
@Value
public class EventReviewDto implements Serializable {
    UserReviewDto user;
    @Size(max = 45)
    String review;
    @NotNull
    Timestamp reviewDate;
}