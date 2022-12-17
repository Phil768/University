package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import okhttp3.*;



public class sendRequest {
	
	public void sendGetRequest() {
		String url = "https://api.marketalertum.com/EventsLog/01150cc0-eff8-4df5-a549-eb18cf7c6184";
		 try {
			HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
			httpClient.setRequestMethod("GET");
			httpClient.setRequestProperty("Content-Type", "application/json");
			try (BufferedReader in = new BufferedReader(
	                new InputStreamReader(httpClient.getInputStream()))) {

	            StringBuilder response = new StringBuilder();
	            String line;

	            while ((line = in.readLine()) != null) {
	                response.append(line);
	            }

	            //print result
	            System.out.println(response.toString());

	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean sendPostRequest(int i){
		//Creating a new alert.
		alertConstruction alert = new alertsToUpload().alerts(i);
		String json = new Gson().toJson(alert);
		boolean alertStatus = false;
		
		OkHttpClient httpClient = new OkHttpClient();
		// form parameters
		RequestBody body = RequestBody.create(
	    		json,
	    		MediaType.parse("application/json; charset=utf-8")
			);

        Request request = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
            	System.out.println("failed");
            	alertStatus = false;
            }
            
            // Get response body
            System.out.println(response.body().string());
            alertStatus = true;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return alertStatus;
	}

}
