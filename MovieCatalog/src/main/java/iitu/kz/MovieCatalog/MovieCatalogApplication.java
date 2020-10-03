package iitu.kz.MovieCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class MovieCatalogApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogApplication.class, args);
	}

}
