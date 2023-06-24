package gov.iti.evento.services.dtos.event;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link gov.iti.evento.entites.Event}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCalendarDto implements Serializable {
    @NotNull
    Timestamp date;
    @NotNull
    @Size(max = 45)
    String title;
}