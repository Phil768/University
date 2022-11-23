package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.tourGuides;

import java.util.LinkedList;
import java.util.List;

public class boatTours
{
    protected String name;
    protected tourGuides Guide;
    protected List<Tourist> tourists;
    protected int size = tourists.size();

    public boatTours(String name, String guideName) {
        this.name = name;
        tourists = new LinkedList<Tourist>();
    }

    public boolean addTourist(Tourist tourist) {
        if(size <= 100) {
            tourists.add(tourist);
            return true;
        }
        return false;
    }

    public double getPrice(){
        return 14.99;
    }

    public void setGuide(tourGuides guide){
        Guide.specialisation = "Boat";
        Guide.name = guide.name;
    }

    public tourGuides getGuide()
    {
        return Guide;
    }

    public double calculateBoatGuide()
    {
        return (getPrice() * 0.25);
    }
}
