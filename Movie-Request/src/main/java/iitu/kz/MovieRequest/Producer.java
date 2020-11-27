package iitu.kz.MovieRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "movie_requests";

    @Autowired
    private KafkaTemplate<String, MovieRequest> kafkaTemplate;

    public String movieRequestNotify(MovieRequest movieRequest) {
        System.out.println(String.format("#### -> Producing movie request to notification service -> %s", movieRequest));
        this.kafkaTemplate.send(TOPIC, movieRequest);
        return "Successfully";
    }
}