package msg.rabbitmq;

import msg.rabbitmq.component.Producer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

//@EnableScheduling
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    // First Simple Test Code Start
//    @Value("${myqueue}")
//    String queue;
//    @Autowired
//    Producer producer;
//
//    @Bean
//    Queue queue() {
//        return new Queue(queue, false);         // durable이 false이기 때문에 서버를 재시작하는 순간 큐는 사라진다.
//    }
//
//    @Bean
//    CommandLineRunner sender(Producer producer) {       // Producer 클래스에 @Component를 붙여놨기 때문에 파라미터로 받을 때 이미 Bean으로 인식한다.
//        return args -> {
//            producer.sendTo(queue, "Hello, World!");
//        };
//    }
//
//    @Scheduled(fixedDelay = 500L)
//    public void sendMsg() {
//        producer.sendTo(queue, "지금 시각은 " + new Date());
//    }
    // First Simple Test Code End
}
