# Spring Boot + RabbitMQ

## 동기? 비동기?
![sync & async](https://mblogthumb-phinf.pstatic.net/20150714_183/tmondev_1436844582787b82mW_PNG/2_2.png?type=w2)

***

## AMQP
프로그래밍에서 MQ는 프로세스 또는 프로그램 인스턴스가 데이터를 서로 교환할 때 사용하는 방법을 말한다.
이러한 MQ의 오픈 소스에 기반한 표준 프로토콜이 AMQP(Advanced Message Queuing Protocol)이다.
AMQP 자체가 프로토콜을 의미하기 때문에 이 프로토콜을 구현한 MQ 제품들은 여러가지가 있으며 그 중 하나가 RabbitMQ이다. 

### AMQP의 구성요소와 라우팅 알고리즘
![AMQP_Algorithm](https://mblogthumb-phinf.pstatic.net/20150714_167/tmondev_1436844582589iKxDW_PNG/3.png?type=w2)

### AMQP의 라우팅 모델은 Exchange와 Queue 그리고 Binding으로 구성된다.
* Exchange는 publisher(Producer)로부터 수신한 메세지를 큐에 분배하는 라우터의 역할을 한다.
* Queue는 메시지를 메모리나 디스크에 저장했다가 Customer에게 메시지를 전달하는 역할을 한다.
* Binding은 Exchange와 Queue의 관계를 정의한 것을 말한다.
* Exchange Type은 메시지를 어떠한 방법으로 라우팅할지 결정하는 일종의 알고리즘을 말하며 AMQP에서는 다음과 같이 Exchange Type을 정의하고 있다.
<table>
<tr>
<td>Exchange Type</td>
<td>정의</td>
</tr>
<tr>
<td>Direct exchange</td>
<td>
Exchange에 바인딩 된 큐 중에서 메시지의 라우팅 키와 매핑되어 있는 큐로 메시지를 전달한다.<br/>
1:1 관계로 Unicast 방식에 적합함, 주로 RR 방식으로 여러 Customers간 task 분리에 사용된다.<br/>
</td>
</tr>
<tr>
<td>Fanout exchange</td>
<td>
메시지의 라우팅 키를 무시하고 Exchange에 바인딩 된 모든 큐에 메시지를 전달한다.<br/>
1:N 관계로 메시지를 브로드캐스팅하는 용도로 사용된다. 
</td>
</tr>
<tr>
<td>Topic exchange</td>
<td>
Exchange에 바인딩 된 큐 중에서 메시지의 라우팅 키가 패턴에 맞는 큐에게 모두 메시지를 전달한다.
Multicast 방식에 적합하다.
</td>
</tr>
<tr>
<td>Headers exchange</td>
<td>
라우팅 키 대신 메시지 헤더에 여러 속성들을 더해 속성들이 매칭되는 큐에 메시지를 전달한다.
</td>
</tr>
</table>

### 이해를 돕기 위한 Exchange Type Image
* Direct Exchange<br/>
![Direct_exchange_routing](http://img1.daumcdn.net/thumb/R1920x0/?fname=http%3A%2F%2Fcfile28.uf.tistory.com%2Fimage%2F275EF93458749D682103C9)

* Fanout Exchange<br/>
![Fanout_exchange_routing](http://img1.daumcdn.net/thumb/R1920x0/?fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F2342EF3958749D741B3AEA)

* Topic Exchange<<br/>
![Topic_exchange_routing](http://img1.daumcdn.net/thumb/R1920x0/?fname=http%3A%2F%2Fcfile26.uf.tistory.com%2Fimage%2F2123AA3C58749D462219DC)

***
        
## RabbitMQ 기본 개념
RabbitMQ는 AMQP를 구현한 오픈 소스 메시지 브로커 소프트웨어로 Publisher(Producer)로부터 메시지를 받아
Customer에게 라우트하는 것이 주된 역할이다. 이를 이용하면 작업 큐, 발행 및 구독, 라우팅, 주제, 원격 프로시저 호출 등의 모델을 구현할 수 있다.<br/>
![RabbitMQ_Tutorials](https://mblogthumb-phinf.pstatic.net/20150714_279/tmondev_1436844582272jYLSG_PNG/5.png?type=w2)

### RabbitMQ의 Exchange Type
RabbitMQ는 AMQP를 구현하였기 때문에 아래의 표와 같이 Exchange Type이 미리 선언된 이름으로 정의되어 있다.
참고로 작업 큐는 Direct exchange가 적합하다.<br/>
<table>
<tr>
<td>이름</td>
<td>RabbitMQ에 기본적으로 미리 선언된 이름</td>
</tr>
<tr>
<td>Direct exchange</td>
<td>(Empty String) and amq.direct</td>
</tr>
<tr>
<td>Fanout exchange</td>
<td>amq.fanout</td>
</tr>
<tr>
<td>Topic exchange</td>
<td>amq.topic</td>
</tr>
<tr>
<td>Headers exchange</td>
<td>amq.match (and amq.headers in RabbitMQ)</td>
</tr>
</table>

### Spring AMQP 라이브러리 준비하기
RabbitMQ를 사용하기 위해서 다음 라이브러리를 pom.xml에 추가한다. 만약 프로젝트에 이미 Spring core 등 라이브러리가 추가되어 있다면
버전에 따라 AMQP 버전을 다르게 가져가야 한다. AMQP 1.4.x.RELEASE는 Spring 4.x에 의존하고,
AMQP 1.3.x.RELEASE는 Spring 3.x에 의존하기 때문이다.
~~~
<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit</artifactId>
    <version>2.0.3.RELEASE</version>
</dependency>
~~~