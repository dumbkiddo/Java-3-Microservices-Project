package iitu.kz.BookingService.Controller;

import iitu.kz.BookingService.Model.Booking;
import iitu.kz.BookingService.Model.BookingType;
import iitu.kz.BookingService.Repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
class BookingController {

    private final BookingRepository petRepository;

//    @HystrixCommand(fallbackMethod = "movieTypesFallback",threadPoolProperties = {
//            @HystrixProperty(name = "coreSize", value = "15"),
//            @HystrixProperty(name = "maxQueueSize", value = "5") })
    @GetMapping("/petTypes")
    // @GetMapping("/movieTypes")
    //public List<BookingType> getPetTypes() {
    //    return petRepository.findBookingTypes();
    //}

    public List<BookingType> movieTypesFallback() {
        List<BookingType> movieList = new ArrayList<>();
        movieList.add(new BookingType(-1, "Not available"));
        return movieList;
    }

   // @HystrixCommand
    @PostMapping("/users/{ownerId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking processCreationForm(
            @RequestBody BookingRequest bookingRequest,
            @PathVariable("ownerId") int ownerId) {
        final Booking pet = new Booking();
        return save(pet, bookingRequest);
    }

//    @HystrixCommand(fallbackMethod = "saveBookingRequestFallback",threadPoolProperties = {
//            @HystrixProperty(name = "coreSize", value = "15"),
//            @HystrixProperty(name = "maxQueueSize", value = "5") })
    @PutMapping("/users/*/bookings/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processUpdateForm(@RequestBody BookingRequest petRequest) {
        int petId = petRequest.getId();
        Booking pet = new Booking();
        save(pet, petRequest);
    }

    public void saveBookingRequestFallback(@RequestBody BookingRequest bookingRequest) {
        int bookingId = -1;
        Booking pet = new Booking();
        save(pet, bookingRequest);
    }

    private Booking save(final Booking pet, final BookingRequest petRequest) {

        pet.setName(petRequest.getName());
        pet.setMovieDate(petRequest.getMovieDate());
        //petRepository.findBookingTypeById(petRequest.getTypeId())
                //.ifPresent(pet::setType);
        log.info("Saving booking {}", pet);
        return pet;
    }
//    @HystrixCommand(fallbackMethod = "findBookingFallback",threadPoolProperties = {
//            @HystrixProperty(name = "coreSize", value = "15"),
//            @HystrixProperty(name = "maxQueueSize", value = "5") })
    //@GetMapping("owners/*/pets/{petId}")
    @GetMapping("users/*/pets/{petId}")
    //
    public BookingDetails findPet(@PathVariable("bookingId") int petId) {
        return new BookingDetails(new Booking());
    }

    public BookingDetails findBookingFallback (@PathVariable("bookingId") int petId) {
        return new BookingDetails(new Booking());
    }


//    private Booking findPetById(int petId) {
//        Optional<Booking> pet = petRepository.findById(petId);
//        if (!pet.isPresent()) {
//            return new Booking();
//        }
//        return pet.get();
//    }

}
