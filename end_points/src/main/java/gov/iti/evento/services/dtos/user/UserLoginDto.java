package gov.iti.evento.services.dtos.user;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserLoginDto implements Serializable {
    @NotNull
    String email;
    @NotNull
    String password;
}
