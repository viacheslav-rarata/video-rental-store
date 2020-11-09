package org.task.rental.domain;

public enum PriceType {
    PREMIUM(40, 2), BASIC(30, 1);

    private final int price;
    private final int bonusPoints;

    PriceType(int price, int bonusPoints) {
        this.price = price;
        this.bonusPoints = bonusPoints;
    }

    public int getPrice() {
        return price;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }
}
