package iitu.kz.NotificationService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;

@Service
public class Consumer {

    @KafkaListener(topics = "movie_requests", groupId = "group_id")
    public void consume(MovieRequest movieRequest) throws IOException {
        Date date=java.util.Calendar.getInstance().getTime();
        System.out.println(String.format("#### -> New notification -> %s", "User has watched movie at " + date));
    }
}