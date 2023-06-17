package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Category;
import gov.iti.evento.services.dtos.CategoryCreateDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toEntity(CategoryCreateDto categoryCreateDto);

    CategoryCreateDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryCreateDto categoryCreateDto, @MappingTarget Category category);
}