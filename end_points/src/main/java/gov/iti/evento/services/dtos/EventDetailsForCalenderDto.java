package gov.iti.evento.services.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDetailsForCalenderDto {
    private Long id;
    private String title;

}
