package com.vallettatourcompany;

import java.util.LinkedList;
import java.util.List;

public class Tour {

    protected String name;
    protected List<Tourist> tourists;

    public Tour(String name) {
        this.name = name;
        tourists = new LinkedList<Tourist>();
    }

    public boolean addTourist(Tourist tourist) {
        if (tourists.size() < 10) {
            tourists.add(tourist);
            return true;
        }
        return false;
    }

    public void removeTourist(Tourist tourist) {
        tourists.remove(tourist);
    }

    public double getPrice() {

        if (tourists.size() <= 5) {
            return 5;
        } else {
            return 4;
        }
    }

}
