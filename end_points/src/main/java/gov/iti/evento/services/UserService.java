package gov.iti.evento.services;

import gov.iti.evento.configuration.JwtService;
import gov.iti.evento.entites.User;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.services.dtos.AuthResponse;
import gov.iti.evento.services.dtos.user.CreateUserDto;
import gov.iti.evento.services.dtos.user.UserLoginDto;
import gov.iti.evento.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;




    public AuthResponse saveUser(CreateUserDto createUserDto){
        User user = userMapper.toEntity(createUserDto);
        user.setIsAdmin((byte) 0x00);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user = userRepository.save(user);
        System.out.println(user.getId());
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
    public boolean checkEmailValid(String email){
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean checkUserValid(UserLoginDto user) {
        return userRepository.existsByEmailIgnoreCaseAndPassword(user.getEmail() , user.getPassword());
    }
    public AuthResponse authenticate(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                )
        );
        User user = userRepository.findByEmailIgnoreCase(userLoginDto.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

}
