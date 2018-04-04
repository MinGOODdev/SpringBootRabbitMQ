package msg.rabbitmq.config;

import msg.rabbitmq.component.Runner;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${myqueue}")
    private String queue;

    @Autowired
    Runner runner;

    @Bean
    CommandLineRunner sender(Runner runner) {
        return args -> {
            runner.sendTo(queue, "Hello RabbitMQ");
        };
    }

    @Scheduled(fixedDelay = 500L)
    public void sendScheduleMessage() {
        runner.sendTo(queue, "Message Delevery : " + new Date());
    }

    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

}
