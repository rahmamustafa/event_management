package gov.iti.evento.services;

import gov.iti.evento.configuration.JwtService;
import gov.iti.evento.entites.User;
import gov.iti.evento.repositories.*;
import gov.iti.evento.services.dtos.AuthResponse;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.dtos.user.CreateUserDto;
import gov.iti.evento.services.dtos.user.UserLoginDto;
import gov.iti.evento.services.mappers.EventMapper;
import gov.iti.evento.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private UserTicketRepository userTicketRepository;

    @Autowired
    EventMapper eventMapper;


    public AuthResponse saveUser(CreateUserDto createUserDto){
        User user = userMapper.toEntity(createUserDto);
        user.setIsAdmin((byte) 0x00);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user = userRepository.save(user);
        System.out.println(user.getId());
        Map<String,Object> claims = new HashMap<>();
        claims.put("isAdmin",user.getIsAdmin());
        claims.put("id",user.getId());
        String jwtToken = jwtService.generateToken(claims,user);
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
        Map<String,Object> claims = new HashMap<>();
        claims.put("isAdmin",user.getIsAdmin());
        claims.put("id",user.getId());
        String jwtToken = jwtService.generateToken(claims,user);
        return AuthResponse.builder().token(jwtToken).build();
    }
    public Integer getUserId(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent())
            return user.get().getId() ;
        return null;
    }

    public List<EventDto> findUpcomingEventsByUserId(Integer userId, int page, int size) throws Exception {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<UserTicketInfo> userTicketInfo=userTicketRepository.findUpcomingEventsByUserId(userId,new Timestamp(System.currentTimeMillis()), pageRequest);
        userTicketInfo.get(0).getEventTicket().getEvent();
        List<EventDto> eventDtos=new ArrayList<>();
        for (UserTicketInfo u: userTicketInfo
             ) {
           eventDtos.add(eventMapper.toDto(u.getEventTicket().getEvent()));
        }
        return eventDtos;

    }
    public long getNumberOfPagesPerUser(int userId,int pageSize) {
        long count=userTicketRepository.countByUser_IdAndEventTicket_Event_EventDateGreaterThan(userId,new Timestamp(System.currentTimeMillis()));
        if(count%pageSize !=0)

            return (long) (count/pageSize)+1;
        else
            return (long) (count/pageSize);
    }
}
