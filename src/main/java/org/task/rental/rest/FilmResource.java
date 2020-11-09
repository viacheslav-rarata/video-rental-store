package org.task.rental.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.rental.domain.Film;
import org.task.rental.repository.FilmRepository;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmResource {

    private final FilmRepository repository;

    @GetMapping
    public List<Film> films() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> film(@PathVariable("id") Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
