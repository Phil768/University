package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class httpPostRequest
{
    public void sendPostRequest(requestObject requestObject) throws IOException, InterruptedException {

        //Creating a new HTTP client.
        HttpClient client = HttpClient.newHttpClient();

        //Creating a new HTTP request.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.marketalertum.com/Alert"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"alertType\":" +requestObject.alertType+"," +
                                "\"heading\": \""+requestObject.heading+"\"," +
                                "\"description\":\""+requestObject.description+"\"," +
                                "\"url\":\""+requestObject.url+"\"," +
                                "\"imageUrl\":\""+requestObject.imageUrl+"\"," +
                                " \"postedBy\":\""+requestObject.postedBy+"\"," +
                                " \"priceInCents\":"+requestObject.priceInCents+"}"))
                .header("Content-Type", "application/json")
                .build();

        //Storing the response of the sent request in a variable.
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Printing out the status code.
        System.out.println(response.statusCode());
        //Printing out the message body of the request.
        System.out.println(response.body());
    }

}
