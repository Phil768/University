package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class httpDeleteRequest {
    public void sendDeleteRequest() throws IOException, InterruptedException {

        //Creating a new HTTP request.
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.marketalertum.com/Alert?userId=01150cc0-eff8-4df5-a549-eb18cf7c6184"))
                .header("Content-type", "application/json")
                .DELETE()
                .build();

        //Creating a new HTTP client.
        var client = HttpClient.newHttpClient();
        //Storing the response of the sent request in a variable.
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Printing ot the status code.
        System.out.println(response.statusCode());
        //Printing out the request message body.
        System.out.println(response.body());
    }
}
