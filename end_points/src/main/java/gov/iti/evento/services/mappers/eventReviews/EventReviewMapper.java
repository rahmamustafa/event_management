package gov.iti.evento.services.mappers.eventReviews;

import gov.iti.evento.entites.EventReview;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import gov.iti.evento.services.mappers.user.UserReviewMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserReviewMapper.class})
public interface EventReviewMapper {
    EventReview toEntity(EventReviewDto eventReviewDto);

    EventReviewDto toDto(EventReview eventReview);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventReview partialUpdate(EventReviewDto eventReviewDto, @MappingTarget EventReview eventReview);
}