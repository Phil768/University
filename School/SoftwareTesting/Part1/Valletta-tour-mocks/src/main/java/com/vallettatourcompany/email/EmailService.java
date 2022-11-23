package com.vallettatourcompany.email;

import com.vallettatourcompany.utils.FileManager;

import java.util.List;

public interface EmailService
{
    public boolean isEmailValid();
    public boolean sendMessage(String t);
}
