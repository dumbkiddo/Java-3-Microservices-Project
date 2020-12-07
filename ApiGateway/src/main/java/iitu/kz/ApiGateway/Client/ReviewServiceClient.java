package iitu.kz.ApiGateway.Client;

import iitu.kz.ApiGateway.Model.Reviews;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Component
@RequiredArgsConstructor
public class ReviewServiceClient {

    private String hostname = "http://review-service/";

    private final WebClient.Builder webClientBuilder;

    public Mono<Reviews> getVisitsForPets(final List<Integer> petIds) {
        return webClientBuilder.build()
            .get()
            .uri(hostname + "pets/visits?petId={petId}", joinIds(petIds))
            .retrieve()
            .bodyToMono(Reviews.class);
    }

    private String joinIds(List<Integer> petIds) {
        return petIds.stream().map(Object::toString).collect(joining(","));
    }

    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
