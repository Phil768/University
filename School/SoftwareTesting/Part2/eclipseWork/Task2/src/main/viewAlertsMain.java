package main;

import java.util.Random;

public class viewAlertsMain {
	public static void main(String[] args){
		//Creating a new login object and initialising its locked state. Default set to false.
		final Login login = new Login(false);
		//Running the runner method using the above created object.
		runner(login);
	}
	
	public static void runner(Login login){
		//Stating that a login request has been created and indicating that we have moved on from the start state.
		login.loginRequest();
		//Initialising the Random class which will be used further on.
		final Random rand = new Random();
		while(true){
			//Getting random number between 1 and 2 to simulate if it is a good or bad login.
			final int loginStatus = rand.nextInt((2 - 1) + 1) + 1;
			//Setting the status of the system accordingly.
			if(loginStatus == 1){
				//Displaying the bad login message.
				login.badLogin();
				//Setting the login status to true.
				login.setLocked(true);
				//Putting the system to sleep to simulate a brief timeout.
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Re-setting the login status.
				login.setLocked(false);
			}
		}
	}
}
