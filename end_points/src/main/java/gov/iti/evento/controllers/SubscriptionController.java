package gov.iti.evento.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import gov.iti.evento.services.SubscriptionService;
import gov.iti.evento.services.dtos.SubscriptionRequest;

@RestController
@RequestMapping("/subscribe")
@CrossOrigin(origins = "http://localhost:4200")
public class SubscriptionController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> subscribeEmail(@RequestBody SubscriptionRequest request) {
        String email = request.getEmail();
        sendSubscriptionEmail(email);

        return ResponseEntity.ok().build();
    }

    private void sendSubscriptionEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Subscription Successful");
        message.setText("Thank you for subscribing to our newsletter!");

        mailSender.send(message);
        subscriptionService.createSubscription(email);
    }
}
