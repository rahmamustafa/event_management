package gov.iti.evento.services.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class CreateUserDto implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String image;
    @NotNull
    private String country;
    @NotNull
    private String gender;
    @NotNull
    private String birthDate;

}
