package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.guides.TouristGuide;

import java.util.LinkedList;
import java.util.List;

public abstract class Tour {

    protected String name;
    protected List<Tourist> tourists;
    protected TouristGuide guide;

    public Tour(String name) {
        this.name = name;
        tourists = new LinkedList<Tourist>();
        guide = null;
    }

    public boolean addTourist(Tourist tourist) {
        if (tourists.size() < getMaxTourists()) {
            tourists.add(tourist);
            return true;
        }
        return false;
    }

    public boolean setGuide(TouristGuide guide) {
        if (guide == null || guide.getSpecialisation() == getTourType()) {
            this.guide = guide;
            return true;
        }

        return false;
    }

    public void removeTourist(Tourist tourist) {
        tourists.remove(tourist);
    }

    public double calculateRevenue() {
        return tourists.size() * getPrice();
    }

    public double calculateGuideCommission() {
        return calculateRevenue() * 0.25;
    }


    //Abstract methods
    public abstract double getPrice();

    public abstract int getMaxTourists();

    public abstract TourType getTourType();
}
