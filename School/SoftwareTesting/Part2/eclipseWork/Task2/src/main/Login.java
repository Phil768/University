package main;

public class Login {
		//Setting the locked/unlocked status of the alert.
		public boolean lockedStatus;
		
		public Login(boolean lockedStatus){
			this.lockedStatus = lockedStatus;
		}
		
		//Method to determine if the alert should enter a blocked state and revert back to the scraper to retry.
		public void setLocked(boolean locked){
			if(locked){
				System.out.println("Login has been locked");
			} else {
				System.out.println("Login has been unlocked");
			}
			this.lockedStatus = locked;
		}
		
		//Setting the methods for when the appropriate events happen.
		public void loginRequest(){
			System.out.println("login request recorded at: " + System.currentTimeMillis());
		}
		public void badLogin(){
			System.out.println("Bad Login recorded at: " + System.currentTimeMillis());
		}
		public void goodLogin(){
			System.out.println("Good Login recorded at: " + System.currentTimeMillis());
		}
		public void viewAlerts(){
			System.out.println("View alerts request at: " + System.currentTimeMillis());
		}
		
		public void viewMonitorType(){
			//Getting the type of the monitor from the API.
		}
}
