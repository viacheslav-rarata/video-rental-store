package org.task.rental.rest;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.task.rental.domain.Rental;
import org.task.rental.service.RentalService;

@Slf4j
@RestController
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalResource {

    private final RentalService service;

    @PostMapping
    public ResponseEntity<Rental> create(@RequestParam int rentedDays, @RequestParam long customerId,
            @RequestParam Long filmId) {
        return ResponseEntity.ok(service.addRental(rentedDays, customerId, filmId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Rental> update(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.update(id));
    }

    @GetMapping
    public Collection<Rental> listRentals() {
        return service.listRentals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> rental(@PathVariable("id") Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleErrors(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
