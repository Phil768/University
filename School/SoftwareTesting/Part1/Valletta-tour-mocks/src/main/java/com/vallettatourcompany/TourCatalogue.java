package com.vallettatourcompany;

import com.vallettatourcompany.guides.TouristGuide;
import com.vallettatourcompany.tours.Tour;

import java.util.LinkedList;
import java.util.List;

public class TourCatalogue {

    protected List<Tour> tours;

    public TourCatalogue() {
        tours = new LinkedList<Tour>();
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void removeTour(Tour tour) {
        tours.remove(tour);
    }

}
