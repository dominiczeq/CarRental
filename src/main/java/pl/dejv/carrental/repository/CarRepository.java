package pl.dejv.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dejv.carrental.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            value = "select * from carrental.car where office_id = :id",
            nativeQuery = true)
    List<Car> findCarByOfficeId(@Param("id") Long id);
}
