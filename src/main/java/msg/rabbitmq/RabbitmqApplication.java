package msg.rabbitmq;

import msg.rabbitmq.component.Producer;
import msg.rabbitmq.config.RabbitMQConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqApplication {

    @Bean
    CommandLineRunner sender(Producer producer) {
        return args -> {
            producer.sendTo(RabbitMQConfig.QUEUE_NAME, "Hello, RabbitMQ!");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
