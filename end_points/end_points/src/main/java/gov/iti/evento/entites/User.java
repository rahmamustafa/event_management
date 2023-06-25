package gov.iti.evento.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable, UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "is_admin" ,nullable = false ,columnDefinition="default '0'")
    private Byte isAdmin;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 250)
    @NotNull
    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Size(max = 45)
    @NotNull
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Size(max = 45)
    @Column(name = "country", length = 45)
    private String country;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

//    @NotNull
    @Column(name = "creation_date")
    private Instant creationDate;

    @Size(max = 45)
    @NotNull
    @Column(name = "gender", nullable = false, length = 45)
    private String gender;

    @Size(max = 45)
    @Column(name = "image", length = 45)
    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(isAdmin==1?"Admin":"User"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
