package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.guides.TouristGuide;
import com.vallettatourcompany.stubs.WeatherServiceFavourable;
import com.vallettatourcompany.stubs.WeatherServiceNotFavourable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoatTourTests {

    final int MAX_TOURISTS = 100;
    final double TOUR_PRICE = 14.99;

    BoatTour boatTour;

    @BeforeEach
    public void setup() {
        boatTour = new BoatTour("Test Walking Tour");
    }

    @Test
    public void testMaxTouristsForBoatTour() {
        Assertions.assertEquals(MAX_TOURISTS, boatTour.getMaxTourists());
    }

    @Test
    public void testNoExtraTouristsAddedToBoatTour() {

        //Setup
        for (int i=0; i<MAX_TOURISTS; i++) {
            boolean result = boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
            Assertions.assertTrue(result);
        }

        //Exercise
        boolean result = boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void testPriceForBoatTourWithZeroTourists() {
        //Exercise
        double price = boatTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE, price);
    }

    @Test
    public void testPriceForBoatTourWithOneTourist() {

        //Setup
        boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double price = boatTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE, price);
    }

    @Test
    public void testPriceForBoatTourWithMaxTourists() {

        //Setup
        for (int i=0; i<MAX_TOURISTS; i++) {
            boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = boatTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE, price);
    }

    public void testAddTourist() {
        //Exercise
        boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertEquals(1, boatTour.tourists.size());
    }

    @Test
    public void testRemoveTourist() {
        //Setup
        Tourist tourist = new Tourist("John Smith", "UK", "email@address");
        boatTour.addTourist(tourist);

        //Exercise
        boatTour.removeTourist(tourist);

        //Verify
        Assertions.assertEquals(0, boatTour.tourists.size());
    }

    @Test
    public void testCorrectTourType() {
        Assertions.assertEquals(TourType.BoatTour, boatTour.getTourType());
    }

    @Test
    public void testGuideWithCorrectSpecilisation() {
        //Exercise
        boolean result = boatTour.setGuide(new TouristGuide("Maya Bertolino", TourType.BoatTour ));

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testGuideWithIncorrectSpecilisation() {
        //Exercise
        boolean result = boatTour.setGuide(new TouristGuide("Maya Bertolino", TourType.WalkingTour ));

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void testTotalPrice() {

        //Setup
        boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double revenue = boatTour.calculateRevenue();

        //Verify
        Assertions.assertEquals(TOUR_PRICE * 2, revenue);
    }

    @Test
    public void testCommission() {

        //Setup
        boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        double revenue = boatTour.calculateRevenue();

        //Exercise
        double commission = revenue * .25;

        //Verify
        Assertions.assertEquals(TOUR_PRICE * 2 * .25, boatTour.calculateGuideCommission());
    }

    @Test
    public void testAddTouristWhenConditionsAreFavourable() {

        //Setup
        boatTour.setWeatherService(new WeatherServiceFavourable());

        //Exercise
        boolean result = boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testAddTouristWhenConditionsAreNotFavourable() {

        //Setup
        boatTour.setWeatherService(new WeatherServiceNotFavourable());

        //Exercise
        boolean result = boatTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertFalse(result);
    }
}
