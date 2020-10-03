package iitu.kz.MovieDescription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MovieDescriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDescriptionApplication.class, args);
	}

}
