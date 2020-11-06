package iitu.kz.BookingService.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import iitu.kz.BookingService.DTO.BookingDTO;
import iitu.kz.BookingService.Model.Booking;
import iitu.kz.BookingService.Repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @HystrixCommand(fallbackMethod = "testFallback")
    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<String>("Booking Service is running",HttpStatus.OK);
    }

    public ResponseEntity<String> testFallback() {
        return new ResponseEntity<String>("Booking Service is responding",HttpStatus.BAD_REQUEST);
    }

    @HystrixCommand
    @PostMapping(value="/addBooking",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> registerNewUser(@Valid @RequestBody BookingDTO orderDto, Errors errors) {

        if(errors.hasErrors()) {
            List<String> errorMsg = new ArrayList<String>();
            errors.getAllErrors().forEach(a -> errorMsg.add(a.getDefaultMessage()));
            return new ResponseEntity<List<String>>(errorMsg, HttpStatus.BAD_REQUEST);
        }else {
            Booking bookingEntity = new Booking();
            bookingEntity.setUserId(orderDto.getUserId());
            bookingEntity.setOrderDate(orderDto.getOrderDate());
            bookingEntity.setTotalAmount(orderDto.getTotalAmount());

            bookingRepository.save(bookingEntity);
            List<String> msgLst = Arrays.asList("Booking was added successfully");
            return new ResponseEntity<List<String>>(msgLst, HttpStatus.OK);
        }
    }
}
