package org.task.rental.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.task.rental.domain.Customer;
import org.task.rental.domain.Film;
import org.task.rental.domain.FilmType;
import org.task.rental.domain.Rental;
import org.task.rental.repository.CustomerRepository;
import org.task.rental.repository.FilmRepository;
import org.task.rental.repository.RentalRepository;

@RunWith(MockitoJUnitRunner.class)
public class RentalServiceTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final Long RENTAL_ID = 1L;

    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private RentalService underTest;

    @Test
    public void testAddRentalForNewReleaseOneDay() {
        Customer customer = initCustomer();
        Film film = initFilm(FilmType.NEW_RELEASE);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.addRental(1, 1L, 1L);
        assertEquals(1, (int) rental.getRentedDays());
        assertNotNull(rental.getRentalDate());
        assertEquals(40, (int) rental.getPrice());
        assertEquals(film, rental.getFilm());
        assertEquals(customer, rental.getCustomer());
        assertEquals(2, (int) customer.getBonusPoints());
        assertNull(rental.getExtraDays());
        assertNull(rental.getSurcharges());
        assertNull(rental.getReturnDate());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testAddRentalForNewReleaseMultipleDays() {
        Customer customer = initCustomer();
        Film film = initFilm(FilmType.NEW_RELEASE);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.addRental(3, 1L, 1L);
        assertEquals(3, (int) rental.getRentedDays());
        assertNotNull(rental.getRentalDate());
        assertEquals(120, (int) rental.getPrice());
        assertEquals(film, rental.getFilm());
        assertEquals(customer, rental.getCustomer());
        assertEquals(2, (int) customer.getBonusPoints());
        assertNull(rental.getExtraDays());
        assertNull(rental.getSurcharges());
        assertNull(rental.getReturnDate());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testAddRentalForRegularReleaseOneDay() {
        Customer customer = initCustomer();
        Film film = initFilm(FilmType.REGULAR_RELEASE);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.addRental(1, 1L, 1L);
        assertEquals(1, (int) rental.getRentedDays());
        assertNotNull(rental.getRentalDate());
        assertEquals(30, (int) rental.getPrice());
        assertEquals(film, rental.getFilm());
        assertEquals(customer, rental.getCustomer());
        assertEquals(1, (int) customer.getBonusPoints());
        assertNull(rental.getExtraDays());
        assertNull(rental.getSurcharges());
        assertNull(rental.getReturnDate());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testAddRentalForRegularReleaseMultipleDays() {
        Customer customer = initCustomer();
        Film film = initFilm(FilmType.REGULAR_RELEASE);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.addRental(5, 1L, 1L);
        assertEquals(5, (int) rental.getRentedDays());
        assertNotNull(rental.getRentalDate());
        assertEquals(90, (int) rental.getPrice());
        assertEquals(film, rental.getFilm());
        assertEquals(customer, rental.getCustomer());
        assertEquals(1, (int) customer.getBonusPoints());
        assertNull(rental.getExtraDays());
        assertNull(rental.getSurcharges());
        assertNull(rental.getReturnDate());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testAddRentalForOldReleaseOneDay() {
        Customer customer = initCustomer();
        Film film = initFilm(FilmType.OLD_RELEASE);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.addRental(1, 1L, 1L);
        assertEquals(1, (int) rental.getRentedDays());
        assertNotNull(rental.getRentalDate());
        assertEquals(30, (int) rental.getPrice());
        assertEquals(film, rental.getFilm());
        assertEquals(customer, rental.getCustomer());
        assertEquals(1, (int) customer.getBonusPoints());
        assertNull(rental.getExtraDays());
        assertNull(rental.getSurcharges());
        assertNull(rental.getReturnDate());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testAddRentalForOldReleaseMultipleDays() {
        Customer customer = initCustomer();
        Film film = initFilm(FilmType.OLD_RELEASE);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.addRental(7, 1L, 1L);
        assertEquals(7, (int) rental.getRentedDays());
        assertNotNull(rental.getRentalDate());
        assertEquals(90, (int) rental.getPrice());
        assertEquals(film, rental.getFilm());
        assertEquals(customer, rental.getCustomer());
        assertEquals(1, (int) customer.getBonusPoints());
        assertNull(rental.getExtraDays());
        assertNull(rental.getSurcharges());
        assertNull(rental.getReturnDate());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testAddRentalWhenCustomerNotFound() {
        try {
            underTest.addRental(7, 1L, 1L);
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("Customer with id 1 not found", ex.getMessage());
        }
        verifyNoInteractions(filmRepository, rentalRepository);
    }

    @Test
    public void testAddRentalWhenFilmNotFound() {
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(initCustomer()));
        try {
            underTest.addRental(7, 1L, 1L);
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("Film with id 1 not found", ex.getMessage());
        }
        verifyNoInteractions(rentalRepository);
    }

    @Test
    public void testUpdateNewReleaseReturnedInTime() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(initRental(FilmType.NEW_RELEASE, 0)));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.update(RENTAL_ID);
        assertNotNull(rental.getReturnDate());
        assertEquals(0, (int) rental.getSurcharges());
        assertEquals(0, (int) rental.getExtraDays());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testUpdateNewReleaseReturnedLate() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(initRental(FilmType.NEW_RELEASE, 2)));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.update(RENTAL_ID);
        assertNotNull(rental.getReturnDate());
        assertEquals(80, (int) rental.getSurcharges());
        assertEquals(2, (int) rental.getExtraDays());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testUpdateRegularReleaseReturnedInTime() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(initRental(FilmType.REGULAR_RELEASE, -1)));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.update(RENTAL_ID);
        assertNotNull(rental.getReturnDate());
        assertEquals(0, (int) rental.getSurcharges());
        assertEquals(0, (int) rental.getExtraDays());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testUpdateRegularReleaseReturnedLate() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(initRental(FilmType.REGULAR_RELEASE, 1)));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.update(RENTAL_ID);
        assertNotNull(rental.getReturnDate());
        assertEquals(30, (int) rental.getSurcharges());
        assertEquals(1, (int) rental.getExtraDays());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testUpdateOldReleaseReturnedInTime() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(initRental(FilmType.REGULAR_RELEASE, 0)));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.update(RENTAL_ID);
        assertNotNull(rental.getReturnDate());
        assertEquals(0, (int) rental.getSurcharges());
        assertEquals(0, (int) rental.getExtraDays());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testUpdateOldReleaseReturnedLate() {
        when(rentalRepository.findById(RENTAL_ID)).thenReturn(Optional.of(initRental(FilmType.REGULAR_RELEASE, 3)));
        ArgumentCaptor<Rental> captor = ArgumentCaptor.forClass(Rental.class);

        Rental rental = underTest.update(RENTAL_ID);
        assertNotNull(rental.getReturnDate());
        assertEquals(90, (int) rental.getSurcharges());
        assertEquals(3, (int) rental.getExtraDays());
        verify(rentalRepository, times(1)).save(captor.capture());
        assertEquals(rental, captor.getValue());
    }

    @Test
    public void testUpdateWhenRentalNotFound() {
        try {
            underTest.update(RENTAL_ID);
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("Rental with id 1 not found", ex.getMessage());
        }
        verify(rentalRepository, times(1)).findById(RENTAL_ID);
        verify(rentalRepository, never()).save(any(Rental.class));
    }

    private Customer initCustomer() {
        return new Customer("John", "Wick");
    }

    private Film initFilm(FilmType filmType) {
        Film film = new Film();
        film.setTitle("Matrix");
        film.setFilmType(filmType);
        return film;
    }

    private Rental initRental(FilmType filmType, int daysOver) {
        Rental rental = new Rental();
        rental.setFilm(initFilm(filmType));
        LocalDateTime localDate = LocalDateTime.now().minusDays(daysOver);
        Date rentalDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        rental.setRentalDate(rentalDate);
        return rental;
    }
}