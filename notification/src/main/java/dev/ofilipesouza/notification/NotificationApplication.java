package dev.ofilipesouza.notification;

import dev.ofilipesouza.amqp.RabbitMQMessageProducer;
import org.apache.tomcat.util.modeler.NotificationInfo;
import org.aspectj.weaver.ast.Not;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
        "dev.ofilipesouza.notification",
        "dev.ofilipesouza.amqp",
})
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "dev.ofilipesouza.clients"
)
public class NotificationApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
//                                        NotificationConfig notificationConfig){
//        return args -> {
//            producer.publish("foo",
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKeys());
//        };
//    }
}
