package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class httpPostRequest
{
    public void sendPostRequest(requestObject requestObject) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

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

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

}
