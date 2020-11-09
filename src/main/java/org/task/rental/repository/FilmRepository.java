package org.task.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.task.rental.domain.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
