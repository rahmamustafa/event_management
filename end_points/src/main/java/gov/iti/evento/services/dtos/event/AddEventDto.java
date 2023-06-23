package gov.iti.evento.services.dtos.event;

import gov.iti.evento.services.util.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddEventDto implements Serializable {
    private  String title;
    private String description;
    private String category;
    private Timestamp eventDate;
    private String location;
    private Set<Integer> speakers;
    private Map<String,Float> ticketPrice;
    private Map<String,Integer> ticketNumber;
    private byte isOnline;
    private String image;
    private EventStatus status;

}
