package gov.iti.evento.services.mappers.user;

import gov.iti.evento.entites.User;
import gov.iti.evento.services.dtos.user.UserReviewDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserReviewMapper {
    User toEntity(UserReviewDto userReviewDto);

    UserReviewDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserReviewDto userReviewDto, @MappingTarget User user);
}