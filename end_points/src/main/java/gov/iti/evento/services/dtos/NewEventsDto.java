package gov.iti.evento.services.dtos;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewEventsDto {
    private int id;
    private Timestamp eventDate;
    private String date;
    private String title;
    private String image;
    public void setId(int id) {
        this.id = id;
    }
    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
        this.setDate(this.eventDate);
    }
    private void setDate(Timestamp date) {
        try {
        LocalDate newDate = LocalDate.ofEpochDay(date.getTime()/ (24 * 60 * 60 * 1000));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        this.date= newDate.format(formatter);
        
    } catch (DateTimeParseException ex) {
        // Handle the exception if the timestamp is invalid
        ex.printStackTrace();
        this.date ="";
    }
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
}
