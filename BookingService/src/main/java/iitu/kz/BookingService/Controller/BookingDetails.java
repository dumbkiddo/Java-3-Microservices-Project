package iitu.kz.BookingService.Controller;

import iitu.kz.BookingService.Model.Booking;
import iitu.kz.BookingService.Model.BookingType;
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
        this.movieDate = booking.getMovieDate();
        this.type = booking.getType();
    }
}

