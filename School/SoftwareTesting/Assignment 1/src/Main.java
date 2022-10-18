/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
//import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlString = "https://api.marketalertum.com/Alert";
        URL url = new URL(urlString);
        //Opening the connection.
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString =
                "{\"alertType\":\"6\"," +
                 "\"heading\":\"Jumper Windows 11 Laptop\"," +
                 "\"description\":\"Test\"," +
                 "\"url\":\"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth\", " +
                 "\"imageUrl\":\"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg\"," +
                " \"postedBy\":\"01150cc0-eff8-4df5-a549-eb18cf7c6184\"," +
                " \"priceInCents\":\"24999\"}";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(new InputStreamReader((new URL(urlString)).openConnection().getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }


    }
}