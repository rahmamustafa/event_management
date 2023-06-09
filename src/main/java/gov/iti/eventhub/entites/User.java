package gov.iti.eventhub.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "is_admin", nullable = false)
    private Byte isAdmin;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "country", length = 45)
    private String country;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "creation_date", nullable = false, length = 45)
    private String creationDate;

    @Column(name = "gender", nullable = false, length = 45)
    private String gender;

}