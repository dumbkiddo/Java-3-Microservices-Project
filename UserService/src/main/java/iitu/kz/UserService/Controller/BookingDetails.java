package iitu.kz.UserService.Controller;

import iitu.kz.UserService.Model.Booking;
import iitu.kz.UserService.Model.BookingType;
import lombok.Data;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Data
class BookingDetails {

    private long id;
    private String name;
    private String user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date movieDate;

    private BookingType type;

    BookingDetails(Booking booking) {
        this.id = booking.getId();
        this.name = booking.getName();
        this.user = booking.getOwner().getFirstName() + " " + booking.getOwner().getLastName();
        this.movieDate = booking.getMovieDate();
        this.type = booking.getType();
    }
}
