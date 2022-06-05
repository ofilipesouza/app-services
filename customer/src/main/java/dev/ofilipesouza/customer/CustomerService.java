package dev.ofilipesouza.customer;

import dev.ofilipesouza.amqp.RabbitMQMessageProducer;
import dev.ofilipesouza.clients.fraud.FraudCheckResponse;
import dev.ofilipesouza.clients.fraud.FraudClient;
import dev.ofilipesouza.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer producer;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);


        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }


        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to oFilipeSouza...",
                        customer.getFirstName())
        );

        producer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-keys");

    }
}
