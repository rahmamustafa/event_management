package gov.iti.evento.services.mappers.user;
import org.mapstruct.MappingConstants;

import gov.iti.evento.entites.User;
import gov.iti.evento.services.dtos.user.UserReviewDto;
import org.mapstruct.*;
import gov.iti.evento.services.util.converters.ImageConverter;
import org.mapstruct.factory.Mappers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserReviewMapper {
//    User toEntity(UserReviewDto userReviewDto);

//    @Mapping(target = "image", expression = "java(ImageConverter.recoverImageFromUrl(user.getImage()))")
UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);

    @Mappings({
            @Mapping(target = "image", expression = "java(recoverImageFromUrl(user.getImage()))")
    })
    UserReviewDto toDto(User user) throws Exception;
//    @ByteArrayToStringMapping
    default String mapByteArrayToString(byte[] bytes) {
        return new String(bytes);
    }
    default byte[] recoverImageFromUrl(String urlText) throws Exception {
       return ImageConverter.recoverImageFromUrl(urlText);
    }

}
