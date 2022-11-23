package com.vallettatourcompany.guides;

import com.vallettatourcompany.tours.TourType;
import com.vallettatourcompany.utils.BankServer;

public class TouristGuide {

    public String name;
    public BankServer bankServer;
    protected TourType specialisation;

    public TouristGuide(String name, TourType specialisation) {
        this.name = name;
        this.specialisation = specialisation;
    }

    public TourType getSpecialisation() {
        return specialisation;
    }
    public void setBankServer(BankServer bankServer) { this.bankServer = bankServer;}
}
