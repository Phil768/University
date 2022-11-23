package org.example.utils;

public interface RequestStatusProvider {
    public static int goodRequest = 0;
    public static int badRequest = 1;

    public int getRequestStatusProvider();
}
