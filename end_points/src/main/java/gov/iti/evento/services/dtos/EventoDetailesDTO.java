package gov.iti.evento.services.dtos;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class EventoDetailesDTO implements Serializable {
    private Instant eventDate;
    private String description;
    private String title;
    private Byte isOnline;
    private String image;

}