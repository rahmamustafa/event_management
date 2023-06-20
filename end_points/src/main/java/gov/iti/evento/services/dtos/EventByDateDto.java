package gov.iti.evento.services.dtos;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventByDateDto {
    private Long id;
    private Timestamp eventDate;
    private String time;
    private String category;
    private String title;
    private boolean online;
    private String image;
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
        this.setTime(eventDate);
    }

    public void setTime(Timestamp time) {
        Instant instant = Instant.ofEpochMilli(time.getTime());
        this.time= instant.atZone(ZoneId.systemDefault()).toLocalTime().toString();
        // System.out.println("time ->"+this.time.toString());
    }
       
    public void setCategory(String category) {
        this.category = category;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }
    public void setImage(String image) {

        this.image = image;
    }


}