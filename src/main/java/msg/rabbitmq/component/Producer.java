package msg.rabbitmq.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
    Producer에서는 routingKey와 message를 받는다.
    routingKey가 Queue 이름이 된다.
    message는 Exchange로 전송되며 Exchange는 message를 Queue로 실어나른다.
     */
    public void sendTo(String routingKey, String message) {
        logger.info("Send > ...");
        this.rabbitTemplate.convertAndSend(routingKey, message);
    }

}
