package com.vallettatourcompany.utils;

public interface TimeProvider {

    public static int MORNING = 0;
    public static int AFTERNOON = 1;
    public static int EVENING = 2;

    public int getDaySegment();
}
