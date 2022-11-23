package com.vallettatourcompany.tours;

public class CoachTour extends Tour {

    public CoachTour(String name) {
        super(name);
    }

    public double getPrice() {
        if (tourists.size() <= 15) {
            return 9.50;
        } else if (tourists.size() <= 30) {
            return 7.50;
        } else {
            return 5.25;
        }
    }

    public int getMaxTourists() {
        return 46;
    }

    public TourType getTourType() {
        return TourType.CoachTour;
    }
}
