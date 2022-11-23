package com.vallettatourcompany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TourTests {

    @Test
    public void testPriceForTourWithZeroTourists() {

        //Setup
        Tour tour = new Tour("Test Tour");

        //Exercise
        double price = tour.getPrice();

        //Verify
        Assertions.assertEquals(5, price);
    }


    @Test
    public void testPriceForTourWithFiveTourists() {

        //Setup
        Tour tour = new Tour("Test Tour");
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));

        //Exercise
        double price = tour.getPrice();

        //Verify
        Assertions.assertEquals(5, price);
    }


    @Test
    public void testPriceForTourWithSixTourists() {

        //Setup
        Tour tour = new Tour("Test Tour");
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));

        //Exercise
        double price = tour.getPrice();

        //Verify
        Assertions.assertEquals(4, price);
    }

    @Test
    public void testPriceForTourWithTenTourists() {

        //Setup
        Tour tour = new Tour("Test Tour");
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));
        tour.addTourist(new Tourist("John Smith", "UK"));

        //Exercise
        double price = tour.getPrice();

        //Verify
        Assertions.assertEquals(4, price);
    }

    @Test
    public void testRemoveTourist()
    {
        //Setup
        Tour tour = new Tour("Test tour");
        Tourist man = new Tourist("Man", "Malta");
        tour.addTourist(man);
        tour.addTourist(new Tourist("B", "UK"));
        tour.addTourist(new Tourist("C", "UK"));
        tour.addTourist(new Tourist("D", "UK"));
        tour.addTourist(new Tourist("E", "UK"));
        tour.addTourist(new Tourist("F", "UK"));

        //Exercise
        int before = tour.tourists.size();
        tour.removeTourist(man);
        int after = tour.tourists.size();

        //Verify
        Assertions.assertNotEquals(before,after);
        System.out.println("Before: " + before);
        System.out.println("After: " + after);
    }

    @Test
    public void testTenTourists()
    {
        //Setup, Exercise
        Tour tour = new Tour("Test tour");
        tour.addTourist(new Tourist("A", "UK"));
        tour.addTourist(new Tourist("B", "UK"));
        tour.addTourist(new Tourist("C", "UK"));
        tour.addTourist(new Tourist("D", "UK"));
        tour.addTourist(new Tourist("E", "UK"));
        tour.addTourist(new Tourist("F", "UK"));
        tour.addTourist(new Tourist("G", "UK"));
        tour.addTourist(new Tourist("H", "UK"));
        tour.addTourist(new Tourist("I", "UK"));
        tour.addTourist(new Tourist("J", "UK"));
        boolean z = tour.addTourist(new Tourist("K", "UK"));



        //Verify
        Assertions.assertEquals(false, z);
        System.out.println(tour.tourists.size());
    }

}
