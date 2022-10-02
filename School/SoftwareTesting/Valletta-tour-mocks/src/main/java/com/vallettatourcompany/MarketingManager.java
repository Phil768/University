package com.vallettatourcompany;

import com.vallettatourcompany.email.EmailService;
import com.vallettatourcompany.utils.FileManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MarketingManager
{
    protected FileManager filemanager;
    protected EmailService emailService;
    protected List<Tourist> tourists;

    public boolean checkDay()
    {
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);

        if(day == 5) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<Tourist> loadTourists()
    {
       if(filemanager != null && emailService.isEmailValid())
       {
           //Assume that the loaded and verified tourists are added to the list.
          filemanager.loadTouristFromFile();
       }

       return tourists;
    }

    public boolean sendEmails() throws InterruptedException {
        List<String> notSent = new ArrayList<>();

        loadTourists().forEach((e) -> {
            //The below is in such a way ('!') in order to make the program proceed to the not sent list and resent the emails.
            if (!emailService.sendMessage(e.toString())) {
                //do nothing.
            } else {
                notSent.add(e.toString());
            }
        });

        if(!notSent.isEmpty()) {
            TimeUnit.SECONDS.sleep(10);
            notSent.forEach((n) -> emailService.sendMessage(n));
            return true;
        }
        return true;
    }

    //To make sure that the objects are not null since they come from an abstract class.
    public void setFileManager(FileManager fileManager) {
        this.filemanager = fileManager;
    }
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
    public void setTourists(List<Tourist> tourists) {
        this.tourists = tourists;
    }
}
