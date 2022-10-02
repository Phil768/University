package com.vallettatourcompany.utils;

public interface BankServer {
    public static int SUCCESS = 0;
    public static int INSUFFICIENT = 1;
    public static int NotFound = 2;
    public static int ERROR = 3;

    public int transferMoney();
}
