package gov.iti.evento.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.iti.evento.services.FileStorageService;
import gov.iti.evento.services.UserService;
import gov.iti.evento.services.dtos.user.CreateUserDto;
import gov.iti.evento.services.dtos.user.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/users" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void createUser(@RequestParam("image") MultipartFile userImage , @RequestParam("user") String userDto)
            throws JsonProcessingException {
        CreateUserDto createUserDto;

        createUserDto = objectMapper.readValue(userDto , CreateUserDto.class);
        String userImageName = fileStorageService.storeFile(userImage , createUserDto.getEmail());
        createUserDto.setImage(userImageName);

        System.out.println(createUserDto);
        userService.saveUser(createUserDto);
    }
    @PostMapping("/api/auth/email/check")
    public boolean checkEmailValid(@RequestBody String email){
        return userService.checkEmailValid(email);
    }
    @PostMapping(value = "/api/auth/login/check" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkUserValid(@RequestBody UserLoginDto user){
        return userService.checkUserValid(user);
    }

}
