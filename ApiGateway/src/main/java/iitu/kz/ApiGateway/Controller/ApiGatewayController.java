package iitu.kz.ApiGateway.Controller;

import iitu.kz.ApiGateway.Model.Reviews;
import iitu.kz.ApiGateway.Model.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import iitu.kz.ApiGateway.Client.UserServiceClient;
import iitu.kz.ApiGateway.Client.ReviewServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gateway")
public class ApiGatewayController {

    private final UserServiceClient userServiceClient;
    private final ReviewServiceClient reviewServiceClient;
    private final ReactiveCircuitBreakerFactory cbFactory;

    @GetMapping(value = "owners/{ownerId}")
   //@GetMapping(value = "users/{userId}")
    public Mono<UserDetails> getOwnerDetails(final @PathVariable int ownerId) {
        return userServiceClient.getOwner(ownerId)
            .flatMap(owner ->
                reviewServiceClient.getVisitsForPets(owner.getPetIds())
                    .transform(it -> {
                        ReactiveCircuitBreaker cb = cbFactory.create("getOwnerDetails");
                        return cb.run(it, throwable -> emptyVisitsForPets());
                    })
                    .map(addVisitsToOwner(owner))
            );

    }

    private Function<Reviews, UserDetails> addVisitsToOwner(UserDetails owner) {
        return visits -> {
            owner.getPets()
                .forEach(pet -> pet.getVisits()
                    .addAll(visits.getItems().stream()
                        .filter(v -> v.getPetId() == pet.getId())
                        .collect(Collectors.toList()))
                );
            return owner;
        };
    }

    private Mono<Reviews> emptyVisitsForPets() {
        return Mono.just(new Reviews());
    }
}
