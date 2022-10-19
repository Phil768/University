package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
    public static void main(String []args) throws IOException, InterruptedException {
        for(int i = 0; i <= 2; i++) {
            requestObject requestObject = new requestObject(1, "Test", "Test", "Test", "Test", "01150cc0-eff8-4df5-a549-eb18cf7c6184", 5);
            httpPostRequest postRequest = new httpPostRequest();
            postRequest.sendPostRequest(requestObject);
            System.out.println(i);
        }
    }
}
