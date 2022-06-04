package dev.ofilipesouza.costumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "dev.ofilipesouza.costumer",
                "dev.ofilipesouza.amqp",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "dev.ofilipesouza.clients"
)
public class CustomerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
