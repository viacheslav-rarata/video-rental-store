package org.task.rental.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class FilmTypeTest {

    @Test
    public void testFilmType() {
        assertEquals(3, FilmType.values().length);
        assertEquals(FilmType.NEW_RELEASE, FilmType.valueOf("NEW_RELEASE"));
        assertEquals(FilmType.REGULAR_RELEASE, FilmType.valueOf("REGULAR_RELEASE"));
        assertEquals(FilmType.OLD_RELEASE, FilmType.valueOf("OLD_RELEASE"));
    }

    @Test
    public void testPriceType() {
        assertEquals(PriceType.PREMIUM, FilmType.NEW_RELEASE.getPriceType());
        assertEquals(PriceType.BASIC, FilmType.REGULAR_RELEASE.getPriceType());
        assertEquals(PriceType.BASIC, FilmType.OLD_RELEASE.getPriceType());
    }

    @Test
    public void testDaysOver() {
        assertEquals(1, FilmType.NEW_RELEASE.getDaysOver());
        assertEquals(3, FilmType.REGULAR_RELEASE.getDaysOver());
        assertEquals(5, FilmType.OLD_RELEASE.getDaysOver());
    }
}