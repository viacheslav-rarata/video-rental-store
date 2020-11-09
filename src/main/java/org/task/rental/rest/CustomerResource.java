package org.task.rental.rest;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.task.rental.domain.Customer;
import org.task.rental.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerRepository repository;

    @GetMapping
    public Collection<Customer> listCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> customer(@PathVariable("id") Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok(repository.save(new Customer(firstName, lastName)));
    }

}
