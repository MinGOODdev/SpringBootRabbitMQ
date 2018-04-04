package msg.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Runner {

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTo(String routingKey, String msg) {
        log.info("Send msg ....");

        this.rabbitTemplate.convertAndSend(routingKey, msg);
    }

}
