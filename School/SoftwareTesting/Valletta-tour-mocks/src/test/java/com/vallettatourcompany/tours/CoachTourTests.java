package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.guides.TouristGuide;
import com.vallettatourcompany.utils.TimeProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoachTourTests {

    final int MAX_TOURISTS = 46;
    final double PRICE_LESS_THAN_SIXTEEN = 9.50;
    final double PRICE_LESS_THAN_THIRTY_ONE = 7.50;
    final double PRICE_MORE_THAN_THIRTY = 5.25;

    CoachTour coachTour;

    @BeforeEach
    public void setup() {
        coachTour = new CoachTour("Test Walking Tour");
    }

    @Test
    public void testMaxTouristsForCoachTour() {
        Assertions.assertEquals(MAX_TOURISTS, coachTour.getMaxTourists());
    }

    @Test
    public void testNoExtraTouristsAddedToCoachTour() {

        for (int i=0; i<MAX_TOURISTS; i++) {
            boolean result = coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
            Assertions.assertTrue(result);
        }

        boolean result = coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        Assertions.assertFalse(result);
    }

    @Test
    public void testPriceForCoachTourWithZeroTourists() {
        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_SIXTEEN, price);
    }

    @Test
    public void testPriceForCoachTourWithOneTourist() {
        //Setup
        coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_SIXTEEN, price);
    }

    @Test
    public void testPriceForCoachTourWithFifteenTourists() {
        //Setup
        for (int i=0; i<15; i++) {
            coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_SIXTEEN, price);
    }

    @Test
    public void testPriceForCoachTourWithSixteenTourists() {
        //Setup
        for (int i=0; i<16; i++) {
            coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_THIRTY_ONE, price);
    }

    @Test
    public void testPriceForCoachTourWithThirtyTourists() {
        //Setup
        for (int i=0; i<30; i++) {
            coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_THIRTY_ONE, price);
    }

    @Test
    public void testPriceForCoachTourWithThirtyOneTourists() {
        //Setup
        for (int i=0; i<31; i++) {
            coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_MORE_THAN_THIRTY, price);
    }

    @Test
    public void testPriceForCoachTourWithMaxTourists() {
        //Setup
        for (int i=0; i<MAX_TOURISTS; i++) {
            coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        }

        //Exercise
        double price = coachTour.getPrice();

        //Verify
        Assertions.assertEquals(PRICE_MORE_THAN_THIRTY, price);
    }

    @Test
    public void testAddTourist() {
        //Exercise
        coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertEquals(1, coachTour.tourists.size());
    }

    @Test
    public void testRemoveTourist() {
        //Setup
        Tourist tourist = new Tourist("John Smith", "UK", "email@address");
        coachTour.addTourist(tourist);

        //Exercise
        coachTour.removeTourist(tourist);

        //Verify
        Assertions.assertEquals(0, coachTour.tourists.size());
    }

    @Test
    public void testCorrectTourType() {
        Assertions.assertEquals(TourType.CoachTour, coachTour.getTourType());
    }

    @Test
    public void testGuideWithCorrectSpecilisation() {
        //Exercise
        boolean result = coachTour.setGuide(new TouristGuide("Maya Bertolino", TourType.CoachTour ));

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testGuideWithIncorrectSpecilisation() {
        //Exercise
        boolean result = coachTour.setGuide(new TouristGuide("Maya Bertolino", TourType.BoatTour ));

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void testTotalPrice() {

        //Setup
        coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Exercise
        double revenue = coachTour.calculateRevenue();

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_SIXTEEN * 2, revenue);
    }

    @Test
    public void testCommission() {

        //Setup
        coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));
        double revenue = coachTour.calculateRevenue();

        //Exercise
        double commission = revenue * .25;

        //Verify
        Assertions.assertEquals(PRICE_LESS_THAN_SIXTEEN * 2 * .25, coachTour.calculateGuideCommission());
    }

    @Test
    public void testCoachTourNotBookedInAfternoon() {

        //Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        Mockito.when(timeProvider.getDaySegment()).thenReturn(TimeProvider.AFTERNOON);

        CoachTour coachTour = new CoachTour("My Coach Tour");
        coachTour.setTimeProvider(timeProvider);

        //Exercise
        boolean result = coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void testCoachTourBookedInMorning() {

        //Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        Mockito.when(timeProvider.getDaySegment()).thenReturn(TimeProvider.MORNING);

        CoachTour coachTour = new CoachTour("My Coach Tour");
        coachTour.setTimeProvider(timeProvider);

        //Exercise
        boolean result = coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testCoachTourNotBookedInEvening() {

        //Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        Mockito.when(timeProvider.getDaySegment()).thenReturn(TimeProvider.EVENING);

        CoachTour coachTour = new CoachTour("My Coach Tour");
        coachTour.setTimeProvider(timeProvider);

        //Exercise
        boolean result = coachTour.addTourist(new Tourist("John Smith", "UK", "email@address"));

        //Verify
        Assertions.assertFalse(result);
    }


}
