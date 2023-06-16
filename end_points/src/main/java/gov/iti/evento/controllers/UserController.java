package gov.iti.evento.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.iti.evento.services.FileStorageService;
import gov.iti.evento.services.UserService;
import gov.iti.evento.services.dtos.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/users" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void createUser(@RequestParam("image") MultipartFile userImage , @RequestParam("user") String userDto)
            throws JsonProcessingException {
        CreateUserDto createUserDto;
        String userImageName = fileStorageService.storeFile(userImage);
        ServletUriComponentsBuilder.fromCurrentContextPath().path(userImageName).toUriString();
        createUserDto = objectMapper.readValue(userDto , CreateUserDto.class);
        createUserDto.setImageUrl(userImage.getOriginalFilename());
        System.out.println(createUserDto);
        userService.saveUser(createUserDto);
    }

}
