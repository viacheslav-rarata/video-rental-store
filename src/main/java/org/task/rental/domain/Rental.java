package org.task.rental.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_id_generator")
    @SequenceGenerator(name = "rental_id_generator", sequenceName = "seq_rental_id", allocationSize = 1)
    private Long id;

    @Column(name = "rented_days")
    private Integer rentedDays;

    @Column(name = "extra_days")
    private Integer extraDays;

    @Column(name = "rental_date")
    private Date rentalDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "price")
    private Integer price;

    @Column(name = "surcharges")
    private Integer surcharges;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
