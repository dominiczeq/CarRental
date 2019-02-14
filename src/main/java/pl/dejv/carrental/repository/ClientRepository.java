package pl.dejv.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dejv.carrental.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
