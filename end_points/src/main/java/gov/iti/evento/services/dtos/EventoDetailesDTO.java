package gov.iti.evento.services.dtos;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Data
public class EventoDetailesDTO implements Serializable {
    private Timestamp eventDate;
    private String description;
    private String title;
    private Integer id;
    private Byte isOnline;
    private String location;
    private byte[] image;



}