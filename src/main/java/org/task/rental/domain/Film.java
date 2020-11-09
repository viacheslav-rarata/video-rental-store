package org.task.rental.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_generator")
    @SequenceGenerator(name = "film_id_generator", sequenceName = "seq_film_id", allocationSize = 1)
    private Long id;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private FilmType filmType;

}
