package gov.iti.evento.services.mappers;

import gov.iti.evento.entites.Event;
import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.dtos.event.AddEventDto;
import gov.iti.evento.services.util.converters.ImageConverter;
import gov.iti.evento.services.dtos.NewEventsDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "categoryType", target = "category.type")
    Event toEntity(EventDto eventDto);

    @Mapping(source = "category", target = "category.type")
    @Mapping(source = "status", target = "status")
    Event toEntity(AddEventDto eventDto);

    // @Mappings({
    // @Mapping(target = "image", expression =
    // "java(recoverImageFromUrl(event.getImage()))"),
    // })
    @Mappings({
            @Mapping(target = "image", expression = "java(recoverImageFromUrl(event.getImage()))"),
            @Mapping(source = "category.type", target = "categoryType")
    })
    EventDto toDto(Event event) throws Exception;

    @Mapping(source = "image", target = "image", qualifiedByName = "toPath")
    NewEventsDto toNewEventDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryType", target = "category.type")
    Event partialUpdate(EventDto eventDto, @MappingTarget Event event);

    default String mapByteArrayToString(byte[] bytes) {
        return null;
    }

    default byte[] recoverImageFromUrl(String urlText) throws Exception {
        return ImageConverter.recoverImageFromUrl(urlText);
    }

    @Mappings({

            @Mapping(target = "category", source = "category.type"),
            @Mapping(target = "online", source = "isOnline", qualifiedByName = "byteToBoolean"),
            @Mapping(source = "image", target = "image", qualifiedByName = "toPath")

    })
    EventByDateDto eventToeventByDateDto(Event event);

    default Page<EventByDateDto> map(Page<Event> eventPage) {
        return eventPage.map(this::eventToeventByDateDto);
    }

    @Named("toPath")
    default String toPath(String image) {
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
        Path imagePath = Paths.get(UPLOAD_DIRECTORY, image);
        boolean fileExists = Files.exists(imagePath);
        if (fileExists) {
            byte[] imageBytes;
            try {
                imageBytes = Files.readAllBytes(imagePath);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                System.out.println("path ->" + imagePath);
                return base64Image;
            } catch (IOException e) {
                return "";
            }
        }
        return "";
    }

    @Named(value = "byteToBoolean")
    default boolean byteToBoolean(byte value) {
        return value != 0;
    }

}
