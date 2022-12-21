package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.*;

public class sendRequest {
	
	public static void main(String []args){
		//sendDeleteRequest();
	}
	
	public static String sendGetRequest() {
		String url = "https://api.marketalertum.com/EventsLog/01150cc0-eff8-4df5-a549-eb18cf7c6184";
		StringBuilder response = null; 
		try {
			HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
			httpClient.setRequestMethod("GET");
			httpClient.setRequestProperty("Content-Type", "application/json");
			try (BufferedReader in = new BufferedReader(
	                new InputStreamReader(httpClient.getInputStream()))) {

	            response = new StringBuilder();
	            String line;

	            while ((line = in.readLine()) != null) {
	                response.append(line);
	            }
	        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
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
	
	public void sendDeleteRequest(){
		OkHttpClient httpClient = new OkHttpClient();
		Request request = new Request.Builder()
        .url("https://api.marketalertum.com/Alert?userId=01150cc0-eff8-4df5-a549-eb18cf7c6184")
        .delete()
        .build();
		
		try (Response response = httpClient.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLoginStatus(){
		//Parsing the Json request.
		@SuppressWarnings("deprecation")
		JsonElement jelement = new JsonParser().parse(sendGetRequest());
		//Converting the string to a Json array.
		JsonArray json = jelement.getAsJsonArray();
		//Getting the required varibale [Whole object -> systemState -> loggedIn].
		JsonElement obj = json.get(0).
				getAsJsonObject().
				get("systemState").
				getAsJsonObject().
				get("loggedIn");
		//Getting the value as a string.
		String status = obj.getAsString();
		//Returning the extracted variable.
		return status;
	}
	
	public int getEventLogType(int i){
		//Parsing the Json request.
		@SuppressWarnings("deprecation")
		JsonElement jelement = new JsonParser().parse(sendGetRequest());
		//Converting the string to a Json array.
		JsonArray json = jelement.getAsJsonArray();
		//Getting the required varibale [Whole object -> eventLogType].
		JsonElement obj = json.get(i).
				getAsJsonObject().
				get("eventLogType");
		//Getting the value as a string.
		int logType = obj.getAsInt();
		//Returning the extracted variable.
		return logType;
	}

}
