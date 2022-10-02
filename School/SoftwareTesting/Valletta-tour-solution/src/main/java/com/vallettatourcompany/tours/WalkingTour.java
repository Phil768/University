package com.vallettatourcompany.tours;

public class WalkingTour extends Tour {

    public WalkingTour(String name) {
        super(name);
    }

    public double getPrice() {

        if (tourists.size() <= 5) {
            return 5;
        } else {
            return 4;
        }

    }

    public int getMaxTourists() {
        return 10;
    }

    public TourType getTourType() {
        return TourType.WalkingTour;
    }
}
