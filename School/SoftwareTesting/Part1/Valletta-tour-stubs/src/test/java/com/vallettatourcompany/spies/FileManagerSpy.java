package com.vallettatourcompany.spies;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.utils.FileManager;

public class FileManagerSpy implements FileManager {

    public int numCallsToSaveTouristToFile = 0;

    public void saveTouristToFile(Tourist tourist) {
        numCallsToSaveTouristToFile++;
    }

}
