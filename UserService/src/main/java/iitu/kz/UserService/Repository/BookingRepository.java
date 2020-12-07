package iitu.kz.UserService.Repository;

import java.util.List;
import java.util.Optional;

import iitu.kz.UserService.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import iitu.kz.UserService.Model.BookingType;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT ptype FROM BookingType ptype ORDER BY ptype.name")
    List<BookingType> findPetTypes();

    @Query("FROM BookingType ptype WHERE ptype.id = :typeId")
    Optional<BookingType> findPetTypeById(@Param("typeId") int typeId);


}

