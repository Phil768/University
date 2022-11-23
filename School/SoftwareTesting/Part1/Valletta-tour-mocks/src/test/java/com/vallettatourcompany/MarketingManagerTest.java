package com.vallettatourcompany;

import com.vallettatourcompany.spies.FileManagerSpy;
import com.vallettatourcompany.stubs.EmailVerify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MarketingManagerTest
{
    MarketingManager manager;

    @Test
    public void testDayOfWeek()
    {
        //Setup
        manager = new MarketingManager();
        //Test
        boolean day = manager.checkDay();
        //Verify
        Assertions.assertFalse(day);
    }

    @Test
    public void testLoadTouristsFromFile()
    {
        //Setup
        FileManagerSpy fileManager = new FileManagerSpy();
        manager = new MarketingManager();
        manager.setFileManager(fileManager);
        manager.setEmailService(new EmailVerify());
        //Test
        manager.loadTourists();
        //Verify
        Assertions.assertEquals(1, fileManager.numCallsToLoadTouristFromFile);
    }

    @Test
    public void testEmailSender() throws InterruptedException {
        //Setup
        List<Tourist> touristList = new ArrayList<>();
        //Dummy tourists
        touristList.add(new Tourist("John", "UK", "Bassa@gmail.com"));
        touristList.add(new Tourist("John", "UK", "Bassa@gmail.com"));
        touristList.add(new Tourist("John", "UK", "Bassa@gmail.com"));
        touristList.add(new Tourist("John", "UK", "Bassa@gmail.com"));
        manager = new MarketingManager();
        manager.setTourists(touristList);
        manager.setEmailService(new EmailVerify());
        //Test
        boolean email = manager.sendEmails();
        //Verify
        Assertions.assertTrue(email);
    }
}
