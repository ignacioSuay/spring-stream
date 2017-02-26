**How to implement asyncronous communication between microservices using Spring Cloud Stream**

Spring Cloud Stream is a framework for building message-driven microservices. 

I will use Spring Cloud Stream in this post to show how to connect to microservices using a queue. 

The idea of this project is that we will have one microservice (publisher) which will expose a Rest endpoint and will accept a string as a message. Once the client hits the rest endpoint, the publisher will send a message to the subscriber microservices using a queue. 
 

 
1. Add dependencies
 
I have created both projects using the http://start.spring.io/ website. 
 
 The publisher microservice has the following dependencies:
 - spring boot
 - spring boot starter web
 - spring cloud starter stream rabbit
 - lombok
  
  The subscriber microservice has the following dependencies:
   - spring boot
   - spring cloud starter stream rabbit
   - lombok
   
 Bear in mind that in this project I am using RabbitMQ as a message broker but we could use any other Massage broker like Reddis, ActiveMq or Kafka.
 Also I like to use Lomkok in my projects because reduces the boilerplate code in Java but we don't actually need it for the goal of this project.
 
Please find below the publisher pom file:
 
And here is the subscriber pom file:

2. Build the publisher microservice

2.1. Create an inteface for the queue

You will need to create an interface with one or more MessageChannels which will represent the queues in the microervice. The queue will need to contain either an @Output annotation for outbound queues or @Input for inbound queues:

[java]
public interface OutputChannel {

    @Output
    MessageChannel output();
}
[/java]
 
 2.2. Create a Rest endpoint 
 
 To visualize this example we will create a rest endpoint which will be accessible from external. The porpouse of this endpoint is to get a string and then send a message to the queue. 
 
 [java]
 @RestController
 @Slf4j
 @RequiredArgsConstructor
 public class MessageResource {
 
     private final MessageChannel output;
 
     @RequestMapping(method = RequestMethod.GET, value = "/sendMessage/{message}")
     public String sendMessage(@PathVariable String message) {
         log.info("Receive message {}", message);
         Message<String> msg = MessageBuilder.withPayload(message).build();
         output.send(msg);
         return "Message " + message + " sent to the publisher";
     }
 }
 [/java]
 
This Rest endpoint receives an  
 
 
