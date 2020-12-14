package iitu.kz.BookingService.Repository;
import iitu.kz.BookingService.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public class BookingRepository extends JpaRepository<Booking, Integer>{
//    @Query("SELECT ptype FROM BookingType ptype ORDER BY ptype.name")
//    public List<BookingType> findBookingTypes();
//
//    @Query("FROM BookingType ptype WHERE ptype.id = :typeId")
//    public Optional<BookingType> findBookingTypeById(@Param("typeId") int typeId);

}
