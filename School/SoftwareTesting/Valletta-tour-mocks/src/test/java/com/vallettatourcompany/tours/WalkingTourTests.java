package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.guides.TouristGuide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WalkingTourTests {

    final int MAX_TOURISTS = 10;
    final double TOUR_PRICE_LESS_THAN_SIX_TOURISTS = 5;
    final double TOUR_PRICE_MORE_THAN_FIVE_TOURISTS = 4;

    WalkingTour walkingTour;

    @BeforeEach
    public void setup() {
        walkingTour = new WalkingTour("Test Walking Tour");
    }

    @Test
    public void testMaxTouristsForWalkingTour() {
        Assertions.assertEquals(MAX_TOURISTS, walkingTour.getMaxTourists());
    }

    @Test
    public void testNoExtraTouristsAddedToWalkingTour() {

        for (int i=0; i<MAX_TOURISTS; i++) {
            boolean result = walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
            Assertions.assertTrue(result);
        }

        boolean result = walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        Assertions.assertFalse(result);
    }

    @Test
    public void testPriceForWalkingTourWithZeroTourists() {
        //Exercise
        double price = walkingTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE_LESS_THAN_SIX_TOURISTS, price);
    }

    @Test
    public void testPriceForWalkingTourWithOneTourist() {

        //Setup
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double price = walkingTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE_LESS_THAN_SIX_TOURISTS, price);
    }

    @Test
    public void testPriceForWalkingTourWithFiveTourists() {

        //Setup
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double price = walkingTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE_LESS_THAN_SIX_TOURISTS, price);
    }

    @Test
    public void testPriceForWalkingTourWithSixTourists() {

        //Setup
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double price = walkingTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE_MORE_THAN_FIVE_TOURISTS, price);
    }

    @Test
    public void testPriceForWalkingTourWithMaxTourists() {

        //Setup
        for (int i=0; i<MAX_TOURISTS; i++) {
            walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = walkingTour.getPrice();

        //Verify
        Assertions.assertEquals(TOUR_PRICE_MORE_THAN_FIVE_TOURISTS, price);
    }

    @Test
    public void testAddTourist() {
        //Exercise
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertEquals(1, walkingTour.tourists.size());
    }

    @Test
    public void testRemoveTourist() {
        //Setup
        Tourist tourist = new Tourist("John Smith", "UK", "email@address");
        walkingTour.addTourist(tourist);

        //Exercise
        walkingTour.removeTourist(tourist);

        //Verify
        Assertions.assertEquals(0, walkingTour.tourists.size());
    }

    @Test
    public void testCorrectTourType() {
        Assertions.assertEquals(TourType.WalkingTour, walkingTour.getTourType());
    }


    @Test
    public void testGuideWithCorrectSpecilisation() {
        //Exercise
        boolean result = walkingTour.setGuide(new TouristGuide("Maya Bertolino", TourType.WalkingTour ));

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testGuideWithIncorrectSpecilisation() {
        //Exercise
        boolean result = walkingTour.setGuide(new TouristGuide("Maya Bertolino", TourType.CoachTour ));

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void testTotalPrice() {

        //Setup
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double revenue = walkingTour.calculateRevenue();

        //Verify
        Assertions.assertEquals(TOUR_PRICE_LESS_THAN_SIX_TOURISTS * 2, revenue);
    }

    @Test
    public void testCommission() {

        //Setup
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        walkingTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        double revenue = walkingTour.calculateRevenue();

        //Exercise
        double commission = revenue * .25;

        //Verify
        Assertions.assertEquals(TOUR_PRICE_LESS_THAN_SIX_TOURISTS * 2 * .25, walkingTour.calculateGuideCommission());
    }
}
