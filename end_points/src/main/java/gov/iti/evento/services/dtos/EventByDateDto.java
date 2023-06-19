package gov.iti.evento.services.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventByDateDto {
    private Long id;
    private LocalDateTime time;
    private String category;
    private String title;
    private boolean online;
    private String image;
}