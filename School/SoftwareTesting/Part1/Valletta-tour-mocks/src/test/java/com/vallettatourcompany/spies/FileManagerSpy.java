package com.vallettatourcompany.spies;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.utils.FileManager;

public class FileManagerSpy implements FileManager {

    //This file manager spy imitates a real file manager by increasing the count of teh variables whenever
    //they are called upon.

    //The variables are always reset to 0.
    public int numCallsToSaveTouristToFile = 0;
    public int numCallsToLoadTouristFromFile = 0;

    public void saveTouristToFile(Tourist tourist) {
        numCallsToSaveTouristToFile++;
    }
    @Override
    public void loadTouristFromFile() {
        numCallsToLoadTouristFromFile++;
    }

}
