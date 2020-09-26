package iitu.kz.MicroservicesServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEurekaServer
@SpringBootApplication
public class MicroservicesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesServerApplication.class, args);
	}

}
