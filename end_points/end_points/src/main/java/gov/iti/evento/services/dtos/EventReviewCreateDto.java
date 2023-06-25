package gov.iti.evento.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventReviewCreateDto {
    Integer userId;
    String review;
    
}
