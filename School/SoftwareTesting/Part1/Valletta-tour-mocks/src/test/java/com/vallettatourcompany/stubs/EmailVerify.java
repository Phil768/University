package com.vallettatourcompany.stubs;

import com.vallettatourcompany.email.EmailService;

import java.util.List;

public class EmailVerify implements EmailService
{
    public boolean isEmailValid() {
        return true;
    }

    @Override
    public boolean sendMessage(String t) {
        return true;
    }

    //Could have added methods which returns false in order to simulate when an email is not sent.
}
