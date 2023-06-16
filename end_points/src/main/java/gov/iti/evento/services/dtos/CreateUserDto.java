package gov.iti.evento.services.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class CreateUserDto implements Serializable {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String imageUrl;
    private String country;
    private String gender;
    private String birthDate;


}
