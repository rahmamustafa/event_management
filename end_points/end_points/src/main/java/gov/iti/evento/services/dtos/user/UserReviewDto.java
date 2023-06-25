package gov.iti.evento.services.dtos.user;

import gov.iti.evento.entites.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserReviewDto implements Serializable {
    @NotNull
    @Size(max = 45)
    String name;

    byte[] image;
}
