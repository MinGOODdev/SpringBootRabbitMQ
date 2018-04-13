package msg.rabbitmq.component;

import msg.rabbitmq.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handler(String message) {
        logger.info("Consumer > " + message);
    }

}
