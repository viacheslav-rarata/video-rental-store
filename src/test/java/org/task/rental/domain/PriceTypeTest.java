package org.task.rental.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class PriceTypeTest {

    @Test
    public void testPriceType() {
        assertEquals(2, PriceType.values().length);
        assertEquals(PriceType.PREMIUM, PriceType.valueOf("PREMIUM"));
        assertEquals(PriceType.BASIC, PriceType.valueOf("BASIC"));
    }

    @Test
    public void testPrice() {
        assertEquals(40, PriceType.PREMIUM.getPrice());
        assertEquals(30, PriceType.BASIC.getPrice());
    }

    @Test
    public void testBonusPoints() {
        assertEquals(2, PriceType.PREMIUM.getBonusPoints());
        assertEquals(1, PriceType.BASIC.getBonusPoints());
    }

}