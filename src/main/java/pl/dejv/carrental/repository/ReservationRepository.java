package pl.dejv.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dejv.carrental.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(
            value = "select * from carrental.reservation where " +
                    "pickup_date BETWEEN CAST(:from AS DATE) AND CAST(:to AS DATE) " +
                    "or " +
                    "return_date BETWEEN CAST(:from AS DATE) AND CAST(:to AS DATE);",
            nativeQuery = true)
    List<Reservation> findAllCarsReservationByDate(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
