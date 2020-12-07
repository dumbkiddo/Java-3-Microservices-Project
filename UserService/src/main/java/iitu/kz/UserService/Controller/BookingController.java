package iitu.kz.UserService.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import iitu.kz.UserService.Exceptions.NotFoundException;
import iitu.kz.UserService.Model.Booking;
import iitu.kz.UserService.Model.BookingType;
import iitu.kz.UserService.Model.User;
import iitu.kz.UserService.Repository.BookingRepository;
import iitu.kz.UserService.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import iitu.kz.UserService.Model.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
class BookingController {

    private final BookingRepository petRepository;
    private final UserRepository ownerRepository;

    @HystrixCommand(fallbackMethod = "movieTypesFallback",threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping("/petTypes")
   // @GetMapping("/movieTypes")
    public List<BookingType> getPetTypes() {
        return petRepository.findPetTypes();
    }

    public List<BookingType> movieTypesFallback() {
        List<BookingType> movieList = new ArrayList<>();
        movieList.add(new BookingType(-1, "Not available"));
        return movieList;
    }

    @HystrixCommand
    @PostMapping("/owners/{ownerId}/pets")
   // @PostMapping("/users/{userId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking processCreationForm(
        @RequestBody BookingRequest petRequest,
       @PathVariable("ownerId") int ownerId) {
       // @PathVariable("userId") int ownerId) {

        final Booking pet = new Booking();
        final Optional<User> optionalOwner = ownerRepository.findById(ownerId);
        User owner = optionalOwner.orElseThrow(() -> new NotFoundException("User "+ownerId+" not found"));
        owner.addPet(pet);
        return save(pet, petRequest);
    }

    @HystrixCommand(fallbackMethod = "saveBookingRequestFallback",threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @PutMapping("/owners/*/pets/{petId}")
//    @PutMapping("/users/*/bookings/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processUpdateForm(@RequestBody BookingRequest petRequest) {
        int petId = petRequest.getId();
        Booking pet = findPetById(petId);
        save(pet, petRequest);
    }

    public void saveBookingRequestFallback(@RequestBody BookingRequest bookingRequest) {
        int bookingId = -1;
        Booking pet = findPetById(bookingId);
        save(pet, bookingRequest);
    }

    private Booking save(final Booking pet, final BookingRequest petRequest) {

        pet.setName(petRequest.getName());
        pet.setMovieDate(petRequest.getMovieDate());
        petRepository.findPetTypeById(petRequest.getTypeId())
            .ifPresent(pet::setType);
        log.info("Saving booking {}", pet);
        return petRepository.save(pet);
    }
    @HystrixCommand(fallbackMethod = "findBookingFallback",threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "15"),
        @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping("owners/*/pets/{petId}")
    //@GetMapping("users/*/bookings/{bookingId}")
    //
    public BookingDetails findPet(@PathVariable("bookingId") int petId) {
        return new BookingDetails(findPetById(petId));
    }

    public BookingDetails findBookingFallback (@PathVariable("bookingId") int petId) {
        return new BookingDetails(findPetById(-1));
    }


    private Booking findPetById(int petId) {
        Optional<Booking> pet = petRepository.findById(petId);
        if (!pet.isPresent()) {
            throw new NotFoundException("Booking "+petId+" not found");
        }
        return pet.get();
    }

}
