package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.CategoryCreateDto;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.util.converters.ImageConverter;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "categoryType", target = "category.type")
    Event toEntity(EventDto eventDto);


    //    @Mappings({
//            @Mapping(target = "image", expression = "java(recoverImageFromUrl(event.getImage()))"),
//    })
    @Mapping(source = "category.type", target = "categoryType")
    EventDto toDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryType", target = "category.type")
    Event partialUpdate(EventDto eventDto, @MappingTarget Event event);

    default String mapByteArrayToString(byte[] bytes) {
        return null;
    }

//    default byte[] recoverImageFromUrl(String urlText) throws Exception {
//        return ImageConverter.recoverImageFromUrl(urlText);
//    }
}
