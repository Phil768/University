package main;

import java.util.Random;

public class sendAlertsMain {
	public static void main(String[] args){
		//Creating a new alert object and initialising its blocked status which is false.
		final Alert alert = new Alert(false);
		//Running the the runner method using the above created object.
		runner(alert);
	}
	
	public static void runner(Alert alert){
		//Initialising the alert status.
		boolean alertStatus;
		final Random rand = new Random();
		//Stating that the alerts have been added to the scraper.
		alert.addAlert();
		while(true){
			//Giving the random method a 50/50 chance to generate a number of acceptable alerts.
			final int numOfAlerts = rand.nextInt(10);
			//Choosing an alert to send by generating a random number.
			int chosenAlert = rand.nextInt((5 - 1) + 1) + 1;
			//Sending an actual alert to the website. If the chosen is 5, its is forced to simulate a bad request which would usually be a special character in the JSON string for example.
			if(chosenAlert == 5){
				alertStatus = false;
			} else{
				alertStatus = new sendRequest().sendPostRequest(chosenAlert);
			}
			//Setting the blocked or successful status of the alerts.
			if(numOfAlerts > 5 || !alertStatus){
				//Displaying the bad request message.
				alert.badRequest();
				//Setting the blocked status to true.
				alert.setBlocked(true);
				//Putting the system to sleep to simulate a brief timeout.
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Re-setting the blocked status.
				alert.setBlocked(false);
			}else{
				//Displaying the appropriate messages since good requests have been generated.
				alert.goodRequest();
				alert.goodResponse();
			}
			//Putting the system to sleep to simulate a brief timeout.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
