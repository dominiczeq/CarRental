package pl.dejv.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dejv.carrental.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
