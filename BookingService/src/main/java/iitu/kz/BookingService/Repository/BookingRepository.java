package iitu.kz.BookingService.Repository;

import iitu.kz.BookingService.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Integer> {
    Booking findByUserId(String userId);
}
