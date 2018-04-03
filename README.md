# Spring Boot + RabbitMQ
* RabbitMQ는 AMQP 프로토콜을 구현한 메시지 큐

### AMQP
* AMQP는 exchange, queue, binding 세가지 중요 컴포넌트로 구성된다.
* exchange : 발행자로부터 수신한 메시지를 큐 또는 다른 exchange로 보내는 라우터 역할. 이때 exchange는 exchange type과 binding이라는 몇 가지 규칙을 따르는 라우팅 알고리즘에 의해 움직인다.
    * exchange type은 <code>기본, 직접, 팬아웃, 토픽, 헤더</code> 5가지가 있습니다.
    * <code>핵심</code>은 라우팅 키와 함께 메시지를 exchange로 보내면 exchage type별로 메시지를 큐로 보낸다는 것
    * <code>(라우팅 키가 맞지 않으면 전달하지 않는다.)</code>
        * 기본(default): 생성한 큐에 모두 자동 바인딩
        * 직접(direct): 라우팅 키와 큐를 1대1 바인딩<br/>
        ![Direct_exchange_routing](http://img1.daumcdn.net/thumb/R1920x0/?fname=http%3A%2F%2Fcfile28.uf.tistory.com%2Fimage%2F275EF93458749D682103C9)
        * 토픽(topic): 라우팅 키에 와일드카드를 추가해서 바인딩할 수 있다.<br/>
        ![Topic_exchange_routing](http://img1.daumcdn.net/thumb/R1920x0/?fname=http%3A%2F%2Fcfile26.uf.tistory.com%2Fimage%2F2123AA3C58749D462219DC)
        * 헤더(headers): 메시지 헤더에 따라 바인딩하는 기능(헤더에 표현식을 쓰면 무엇이든 가능한 아주 강력한 exchange)
        * 팬아웃(fanout): 메시지를 바인딩한 큐 전체에 복사한다. 즉, 메시지를 방송하는 셈<br/>
        ![Fanout_exchange_routing](http://img1.daumcdn.net/thumb/R1920x0/?fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F2342EF3958749D741B3AEA)
        
### RabbitMQ 기본 개념
1. Message : 일정 구조를 지닌다.
    * header
        * message id
        * timestamp
        * content type
    * body
        * data
        
2. Queue : Message가 쌓이는 구성요소, 즉 Consumer가 Message를 받는 구성요소
    * name: Queue 이름
    * durable: 대기열을 유지할지를 정할 flag
    * exclusive: 선언된 연결에 의해서만 사용할지를 정할 flag
    * auto-delete: 더 이상 사용되지 않는 큐를 삭제할지 정할 flag
        
### RabbitTemplate
* 메시지를 주고받기 위해 RabbitMQ에 동기 접속하는 과정을 단순화한다.

### sendTo(routingKey, msg)
* 라우팅 키와 메시지를 파라미터로 받는 메소드. 라우팅 키가 곧 큐 이름이다.
* 내부에서는 라우팅 키와 메시지를 받는 rabbitTemplate 인스턴스의 convertAndSend 메소드를 호출한다.

### @RabbitListener
* 수신한 메시지를 처리할 handler 메소드를 지정한다.
* RabbitMQ 큐를 감시할 Listener를 만들어 메시지가 들어오면 handler 메소드에 전달한다.
* Listener는 내부적으로 메시지를 적절한 타입으로 변환한다.(MessageConverter Interface를 구현한 적절한 메시지 변환기로)


<code>http://projects.spring.io/spring-amqp</code>