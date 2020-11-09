package org.task.rental.domain;

public enum FilmType {
    NEW_RELEASE(PriceType.PREMIUM, 1),
    REGULAR_RELEASE(PriceType.BASIC, 3),
    OLD_RELEASE(PriceType.BASIC, 5);

    private final PriceType priceType;
    private final int daysOver;

    FilmType(PriceType priceType, int daysOver) {
        this.priceType = priceType;
        this.daysOver = daysOver;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public int getDaysOver() {
        return daysOver;
    }
}
