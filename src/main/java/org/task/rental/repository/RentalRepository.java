package org.task.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.task.rental.domain.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
