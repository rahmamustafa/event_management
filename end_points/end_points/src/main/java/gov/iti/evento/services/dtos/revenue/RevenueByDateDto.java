package gov.iti.evento.services.dtos.revenue;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RevenueByDateDto {
    private Timestamp eventDate;
    private Double y;
    private String x;

    public RevenueByDateDto(Timestamp eventDate, Double revenue) {
        this.eventDate = eventDate;
        this.y = revenue;
        this.setX(this.eventDate);
    }
    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
        this.setX(this.eventDate);
    }
    public void setY(Double revenue) {
        this.y = revenue;
    }
    public void setX(Timestamp date) {
         try {
        LocalDate newDate = LocalDate.ofEpochDay(date.getTime()/ (24 * 60 * 60 * 1000));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.x= newDate.format(formatter);
        }catch (DateTimeParseException ex) {
        // Handle the exception if the timestamp is invalid
        ex.printStackTrace();
        this.x ="";
     }
    }
    
}

