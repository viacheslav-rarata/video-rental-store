package org.task.rental.service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.task.rental.domain.Customer;
import org.task.rental.domain.Film;
import org.task.rental.domain.FilmType;
import org.task.rental.domain.Rental;
import org.task.rental.repository.CustomerRepository;
import org.task.rental.repository.FilmRepository;
import org.task.rental.repository.RentalRepository;

@Slf4j
@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final FilmRepository filmRepository;
    private final CustomerRepository customerRepository;

    public Optional<Rental> findById(Long id) {
        return rentalRepository.findById(id);
    }

    public List<Rental> listRentals() {
        return rentalRepository.findAll();
    }

    @Transactional
    public Rental addRental(Integer rentedDays, Long customerId, Long filmId) {
        log.info("Storing new rental for customerId {} filmId {} for {} days", customerId, filmId, rentedDays);
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw new IllegalArgumentException(String.format("Customer with id %s not found", customerId));
        }
        Optional<Film> filmOpt = filmRepository.findById(filmId);
        if (!filmOpt.isPresent()) {
            throw new IllegalArgumentException(String.format("Film with id %s not found", filmId));
        }
        Film film = filmOpt.get();
        FilmType filmType = film.getFilmType();
        Customer customer = customerOpt.get();
        Rental rental = new Rental();
        rental.setRentedDays(rentedDays);
        rental.setRentalDate(new Date());
        rental.setPrice(calculatePrice(rentedDays, filmType));
        rental.setFilm(film);
        rental.setCustomer(customer);
        customer.addBonusPoints(filmType.getPriceType().getBonusPoints());
        rentalRepository.save(rental);
        return rental;
    }

    @Transactional
    public Rental update(Long rentalId) {
        log.info("Returning rental for rentalId {}", rentalId);
        Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
        if (!rentalOpt.isPresent()) {
            throw new IllegalArgumentException(String.format("Rental with id %s not found", rentalId));
        }
        Rental rental = rentalOpt.get();
        rental.setReturnDate(new Date());
        rental.setExtraDays(calculateExtraDays(rental.getRentalDate(), rental.getReturnDate()));
        rental.setSurcharges(calculateSurcharges(rental.getExtraDays(), rental.getFilm().getFilmType()));
        rentalRepository.save(rental);
        return rental;
    }

    private int calculatePrice(Integer days, FilmType filmType) {
        int price = filmType.getPriceType().getPrice();
        int daysOver = days - filmType.getDaysOver();
        return days > 1 ? price + daysOver * price : filmType.getPriceType().getPrice();
    }

    private int calculateExtraDays(Date rentalDate, Date returnDate) {
        long between = ChronoUnit.DAYS.between(rentalDate.toInstant(), returnDate.toInstant());
        return between > 0 ? (int) between : 0;
    }

    private int calculateSurcharges(int extraDays, FilmType filmType) {
        return extraDays > 0 ? extraDays * filmType.getPriceType().getPrice() : 0;
    }
}
