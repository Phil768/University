package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.tourGuides;

import java.util.LinkedList;
import java.util.List;

public class coachTours {
    protected String name;
    protected double price;
    protected List<Tourist> tourists;
    protected int size = tourists.size();
    protected tourGuides Guide;

    public coachTours(String name) {
        this.name = name;
        tourists = new LinkedList<Tourist>();
    }

    public boolean addTourist(Tourist tourist) {
        if(size <= 46) {
            tourists.add(tourist);
            return true;
        }
        return false;
    }

    public double getPrice(){
        if(size >= 0 && size <= 15)
            return this.price = 9.5;
        else if (size >= 16 && price <= 30)
            return this.price = 7.5;
        else if(size >= 31)
            return this.price = 5.25;

        return 0;
    }

    public void setGuide(tourGuides guide){
        Guide.specialisation = "Coach";
        Guide.name = guide.name;
    }

    public tourGuides getGuide()
    {
        return Guide;
    }


    public double calculateCoachGuide()
    {
        return (getPrice() * 0.25);
    }
}
