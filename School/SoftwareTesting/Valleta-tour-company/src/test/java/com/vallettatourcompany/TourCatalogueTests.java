package com.vallettatourcompany;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TourCatalogueTests {

    TourCatalogue catalogue;
    Tour tour;

    @BeforeEach
    public void setup() {
        catalogue = new TourCatalogue();
        tour = new Tour("Test Tour");
    }

    @AfterEach
    public void teardown() {
        catalogue = null;
        tour = null;
    }

    @Test
    public void testEmptyTourCatalogue() {
        //Verify
        Assertions.assertEquals(0, catalogue.tours.size());
    }

    @Test
    public void testTourCatalogueWithOneTour() {
        //Exercise
        catalogue.addTour(tour);

        //Verify
        Assertions.assertEquals(1, catalogue.tours.size());
    }

    @Test
    public void testRemoveTourFromCatalogue() {
        //Setup
        catalogue.addTour(tour);

        //Exercise
        catalogue.removeTour(tour);

        //Verify
        Assertions.assertEquals(0, catalogue.tours.size());
    }

}
