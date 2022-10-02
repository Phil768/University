package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.tourGuides;

import java.util.List;
import java.util.LinkedList;

public class walkingTours
{
    protected String name;
    protected double price;
    protected List<Tourist> tourists;
    protected tourGuides Guide;
    protected int size = tourists.size();

    public walkingTours(String name) {
        this.name = name;
        tourists = new LinkedList<Tourist>();
    }

    public boolean addTourist(Tourist tourist) {
        tourists.add(tourist);
        return true;
    }

    public double getPrice(){
        if (tourists.size() <= 5)
            return 5;
        else
            return 4;
    }

    public void setGuide(tourGuides guide){
        Guide.specialisation = "Walk";
        Guide.name = guide.name;
    }

    public tourGuides getGuide()
    {
        return Guide;
    }

    public double calculateWalkGuide()
    {
        return (getPrice() * 0.25);
    }
}
