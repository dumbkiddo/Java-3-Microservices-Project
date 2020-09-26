package iitu.kz.BookingService.Model;

public class Booking {
    private String bookingId;
    private User user;
    private CatalogMovie movie;

    public Booking(){

    }

    public Booking(String bookingId, User user,CatalogMovie movie) {
        this.bookingId = bookingId;
        this.user = user;
        this.movie = movie;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(String userName) {
        this.user = user;
    }

    public CatalogMovie getMovie() {
        return movie;
    }

    public void setMovie(String movieName) {
        this.movie = movie;
    }
}
