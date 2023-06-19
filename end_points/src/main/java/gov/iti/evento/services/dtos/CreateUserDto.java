package gov.iti.evento.services.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class CreateUserDto implements Serializable {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String image;
    private String country;
    private String gender;
    private String birthDate;


}
