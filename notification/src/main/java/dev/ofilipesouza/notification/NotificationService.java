package dev.ofilipesouza.notification;

import dev.ofilipesouza.clients.notification.NotificationClient;
import dev.ofilipesouza.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerName())
                .sender("ofilipesouza")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build());
    }
}
