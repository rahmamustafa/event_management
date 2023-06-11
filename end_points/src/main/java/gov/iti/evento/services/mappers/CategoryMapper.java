package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Category;
import gov.iti.evento.services.dtos.CategoryCreateDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryCreateDto categoryCreateDto);

    CategoryCreateDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryCreateDto categoryCreateDto, @MappingTarget Category category);
}