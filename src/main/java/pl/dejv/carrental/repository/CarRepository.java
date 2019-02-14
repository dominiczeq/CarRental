package pl.dejv.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dejv.carrental.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
