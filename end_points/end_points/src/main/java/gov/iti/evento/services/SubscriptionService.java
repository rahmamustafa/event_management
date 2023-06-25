
package gov.iti.evento.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gov.iti.evento.entites.Subscription;
import gov.iti.evento.entites.User;
import gov.iti.evento.repositories.SubscriptionRepository;
import gov.iti.evento.repositories.UserRepository;

@Service
public class SubscriptionService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final JavaMailSender mailSender;

    public SubscriptionService(JavaMailSender mailSender,UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.mailSender = mailSender;
    }

    public void createSubscriptionByuserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscribedAt(LocalDateTime.now());

        subscriptionRepository.save(subscription);
    }
    public void createSubscription(String email) {

        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setUser(null);
        subscription.setSubscribedAt(LocalDateTime.now());

        subscriptionRepository.save(subscription);
    }
      public void notifySubscribers(List<Subscription> emails, String eventName) {
        String subject = "New Event: " + eventName;
        String message = "A new event has been added: " + eventName;

        for (Subscription email : emails) {
            sendEmail(email.getEmail(), subject, message);
        }
    }

    private void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
