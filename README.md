# Java-3 Microservices Project - booking movie tickets
Implementing simple microservices with Spring and Eureka Server in IntelliJ Idea.

##### All database information in -> recources -> Database 
##### All frontend code in ApiGateway -> recources -> static -> Frontend
 
#### Microservices information 
 
#### Eureka server
- **http://localhost:8761** - Viewing running services on Eureka Server 
#### Gateway 
- **http://localhost:8081** - Home page
#### User - manage user details and bookings
- **http://localhost:8082**
#### Movie - manage available movies
- **http://localhost:8083**
#### Review - add reviews to booked movies
- **http://localhost:8084**


### Hystrix dashboard sample with Movies
- **http://localhost:8083/hystrix**
- **http://localhost:8083/actuator/hystrix.stream**
- **http://localhost:8083/movies**

### Kafka demonstration with Cygwin64 terminal
- **Clean tmp log files**

- **In first terminal:**
- **$ cd C:/Users/Hild/Desktop/kafka_2.13-2.6.0**
- **$ bin/zookeeper-server-start.sh config/zookeeper.properties**

- **In second terminal:**
- **$ cd C:/Users/Hild/Desktop/kafka_2.13-2.6.0**
- **$ bin/kafka-server-start.sh config/server.properties**

#### Request (producer)
- **http://localhost:8090**
- **Sample request**
- **http://localhost:8090/movie/request?movieId=1**

#### Notification (consumer)
- **http://localhost:9000**


