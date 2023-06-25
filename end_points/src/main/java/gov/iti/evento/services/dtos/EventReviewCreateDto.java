package gov.iti.evento.services.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class EventReviewCreateDto{
    int id;
    String review;
    int user_id;
}
