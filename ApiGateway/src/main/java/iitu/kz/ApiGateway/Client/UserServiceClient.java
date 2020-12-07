package iitu.kz.ApiGateway.Client;

import iitu.kz.ApiGateway.Model.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    public Mono<UserDetails> getOwner(final int ownerId) {
        return webClientBuilder.build().get()
            .uri("http://user-service/owners/{ownerId}", ownerId)
            .retrieve()
            .bodyToMono(UserDetails.class);
    }
}
