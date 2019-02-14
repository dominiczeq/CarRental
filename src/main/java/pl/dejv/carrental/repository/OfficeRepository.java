package pl.dejv.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dejv.carrental.entity.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
