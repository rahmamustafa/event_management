package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.User;
import gov.iti.evento.services.dtos.user.CreateUserDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(CreateUserDto createUserDto);
    CreateUserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(CreateUserDto createUserDto, @MappingTarget User user);
}