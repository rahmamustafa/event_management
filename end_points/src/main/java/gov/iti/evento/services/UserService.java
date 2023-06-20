package gov.iti.evento.services;

import gov.iti.evento.entites.User;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.services.dtos.CreateUserDto;
import gov.iti.evento.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.valueOf;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public void saveUser(CreateUserDto createUserDto){
        User user = userMapper.toEntity(createUserDto);
        user.setIsAdmin((byte) 0x00);
        user = userRepository.save(user);
        System.out.println(user.getId());
    }
    public boolean checkEmailValid(String email){
        return userRepository.existsByEmailIgnoreCase(email);
    }
}
