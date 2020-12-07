package iitu.kz.UserService.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import iitu.kz.UserService.Exceptions.NotFoundException;
import iitu.kz.UserService.Model.User;
import iitu.kz.UserService.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/owners")
//@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@Slf4j
class UserController {

    private final UserRepository userRepository;

    @HystrixCommand
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createOwner(@Valid @RequestBody User owner) {
        return userRepository.save(owner);
    }

    @HystrixCommand(fallbackMethod = "findUserFallback",threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping(value = "/{ownerId}")

    public Optional<User> findOwner(@PathVariable("ownerId") int ownerId) {
        return userRepository.findById(ownerId);
    }

    public Optional<User> findUserFallback(@PathVariable("ownerId") int ownerId) {
        return userRepository.findById(-1);
    }

    @HystrixCommand
    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @HystrixCommand(fallbackMethod = "updateUserFallback",threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @PutMapping(value = "/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updateOwner(@PathVariable("ownerId") int ownerId, @Valid @RequestBody User ownerRequest) {
        final Optional<User> user = userRepository.findById(ownerId);
        final User userModel = user.orElseThrow(() -> new NotFoundException("User "+ownerId+" not found"));
        userModel.setFirstName(ownerRequest.getFirstName());
        userModel.setLastName(ownerRequest.getLastName());
        userModel.setCity(ownerRequest.getCity());
        userModel.setTelephone(ownerRequest.getTelephone());
        log.info("Saving user {}", userModel);
        userRepository.save(userModel);
    }

    public void updateUserFallback(@PathVariable("ownerId") int ownerId, @Valid @RequestBody User ownerRequest) {
        final Optional<User> user = userRepository.findById(-1);
        final User userModel = user.orElseThrow(() -> new NotFoundException("User "+ownerId+" not found"));
        log.info("User is not available", userModel);
    }
}
