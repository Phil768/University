package com.vallettatourcompany.guides;

import com.vallettatourcompany.tours.TourType;

public class TouristGuide {

    public String name;
    protected TourType specialisation;

    public TouristGuide(String name, TourType specialisation) {
        this.name = name;
        this.specialisation = specialisation;
    }

    public TourType getSpecialisation() {
        return specialisation;
    }
}
