
package gov.iti.evento.services;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import gov.iti.evento.entites.Subscription;
import gov.iti.evento.entites.User;
import gov.iti.evento.repositories.SubscriptionRepository;
import gov.iti.evento.repositories.UserRepository;

@Service
public class SubscriptionService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
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
}
