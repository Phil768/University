package main;

import java.util.Random;

public class sendRequestsMain {
	public static void main(String[] args){
		//Creating a new alert object and initialising its blocked status which is false.
		final Requests request = new Requests(false);
		//Running the the runner method using the above created object.
		runner(request);
	}
	
	public static void runner(Requests request){
		//Initialising the alert status.
		boolean alertStatus = true;
		int logType = 1;
		final Random rand = new Random();
		while(true){
			//Giving the random method a 50/50 chance to generate a number of acceptable alerts.
			final int numOfAlerts = rand.nextInt((10 - 1) + 1) + 1;
			//Choosing an alert to send by generating a random number.
			int chosenAlert = rand.nextInt((5 - 1) + 1) + 1;
			//Sending an actual alert to the website. If the chosen is 5, its is forced to simulate a bad request which would usually be a special character in the JSON string for example.
			if(chosenAlert == 5){
				alertStatus = false;
			} else if(numOfAlerts <= 5){
				//Stating that the alerts have been added to the scraper.
				request.addAlert();
				//Creating a number of alerts.
				for(int i = 0; i < numOfAlerts; i++){
					//Generating a random number for each loop to create different alerts. 
					chosenAlert = rand.nextInt((4 - 1) + 1) + 1;
					alertStatus = new sendRequest().sendPostRequest(chosenAlert);
				}
				//Getting the eventLog type after sending the request.
				logType = new sendRequest().getEventLogType(0);
			}
			//Setting the blocked or successful status of the alerts.
			if(numOfAlerts > 5 || !alertStatus){
				//Displaying the bad request message.
				request.badRequest();
				//Setting the blocked status to true.
				request.setBlocked(true);
				//Putting the system to sleep to simulate a brief timeout.
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Re-setting the blocked status.
				request.setBlocked(false);
			}else if(logType == 0){
				//Displaying the appropriate messages since good requests have been generated.
				request.goodRequest();
				request.goodResponse();
				//Deleting the requests.
				new sendRequest().sendDeleteRequest();
				//Getting the log type from the API.
				logType = new sendRequest().getEventLogType(0);
				//Checking that it was the correct event.
				if(logType == 1){
					request.goodDelete();
					request.goodResponse();
				}
			}else{
				System.out.println("Something went wrong!");
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
