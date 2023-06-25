package gov.iti.evento.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.FileStorageService;
import gov.iti.evento.services.SpeakerAdminService;
import gov.iti.evento.services.UserService;
import gov.iti.evento.services.dtos.AuthResponse;
import gov.iti.evento.services.dtos.SpeakerAdminDto;
import gov.iti.evento.services.dtos.user.CreateUserDto;
import gov.iti.evento.services.dtos.user.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final UserService userService;
    private final FileStorageService fileStorageService;
    private final ObjectMapper objectMapper;

    private final SpeakerAdminService speakerService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestParam("image") MultipartFile userImage
            , @RequestParam("user") String userDto)
            throws JsonProcessingException {

        CreateUserDto createUserDto;
        createUserDto = objectMapper.readValue(userDto, CreateUserDto.class);
        String userImageName = fileStorageService.storeFile(userImage, createUserDto.getEmail(), "Users");
        createUserDto.setImage(userImageName);
        System.out.println(createUserDto);
        return ResponseEntity.ok(userService.saveUser(createUserDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody UserLoginDto request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/email/check")
    public boolean checkEmailValid(@RequestBody String email) {
        return userService.checkEmailValid(email);
    }

    // @PostMapping("/addspeaker")
    // public ResponseEntity<SpeakerAdminDto> addSpeaker(@RequestParam("image") MultipartFile userImage
    //         , @RequestParam("speaker") String userDto)
    //         throws Exception {
    //     Speaker speakerAdminDto;
    //     speakerAdminDto = objectMapper.readValue(userDto, Speaker.class);
    //     String userImageName = fileStorageService.storeFile(userImage, speakerAdminDto.getName(), "Speakers");
    //     speakerAdminDto.setImage(userImageName);
    //     return ResponseEntity.ok(speakerService.addSpeakerByAdmin(speakerAdminDto));
    // }

}
