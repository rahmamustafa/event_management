package gov.iti.eventhub.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "speaker")
public class Speaker {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "job_title", length = 45)
    private String jobTitle;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

}