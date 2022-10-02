package com.vallettatourcompany.tours;

import com.vallettatourcompany.Tourist;
import com.vallettatourcompany.spies.FileManagerSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TourTests {

    @Test
    public void testTouristSavedToFile() {
        //Setup
        FileManagerSpy fileManager = new FileManagerSpy();
        WalkingTour tour = new WalkingTour("Walking tour");
        tour.setFileManager(fileManager);

        //Exercise
        tour.addTourist(new Tourist("John Smith", "UK"));

        //Verify
        Assertions.assertEquals(1, fileManager.numCallsToSaveTouristToFile);
    }

}
