package iitu.kz.BookingService.Service;

import iitu.kz.BookingService.Model.Booking;
import iitu.kz.BookingService.Model.CatalogMovie;
import iitu.kz.BookingService.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bookings")
public class BookingService {

    @RequestMapping("/{bookingID}")
    public Booking getInfo(@PathVariable("bookingID") String bookingID, User user, CatalogMovie movie){
        return new Booking(bookingID,user,movie);
    }


}
