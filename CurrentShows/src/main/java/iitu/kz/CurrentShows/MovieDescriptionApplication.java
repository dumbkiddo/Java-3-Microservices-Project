package iitu.kz.CurrentShows;

import feign.RequestInterceptor;
import iitu.kz.CurrentShows.Feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication (scanBasePackages={"iitu.kz.CurrentShows.MovieCatalogProxy"})
public class MovieDescriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDescriptionApplication.class, args);
	}

	@Bean
	public RequestInterceptor getFeignClientInterceptor() {
		return new Feign();
	}
}
