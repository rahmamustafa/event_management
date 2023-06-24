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
    private Double revenue;
    private String date;

    public RevenueByDateDto(Timestamp eventDate, Double revenue) {
        this.eventDate = eventDate;
        this.revenue = revenue;
        this.setDate(this.eventDate);
    }
    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
        this.setDate(this.eventDate);
    }
    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
    public void setDate(Timestamp date) {
         try {
        LocalDate newDate = LocalDate.ofEpochDay(date.getTime()/ (24 * 60 * 60 * 1000));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        this.date= newDate.format(formatter);
        }catch (DateTimeParseException ex) {
        // Handle the exception if the timestamp is invalid
        ex.printStackTrace();
        this.date ="";
     }
    }
    
}

