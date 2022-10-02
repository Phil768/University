package com.vallettatourcompany.tours;

public class BoatTour extends Tour {

    public BoatTour(String name) {
        super(name);
    }

    public double getPrice() {
        return 14.99;
    }

    public int getMaxTourists() {
        return 100;
    }

    public TourType getTourType() {
        return TourType.BoatTour;
    }
}
