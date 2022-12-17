package main;

public class Alert {
	//Setting the blocked/unblocked status of the alert.
	public boolean blockedStatus;
	//Constructor.
	public Alert(boolean blockedStatus){
		super();
		this.blockedStatus = blockedStatus;
	}
	//Method to determine if the alert should enter a blocked state and revert back to the scraper to retry.
	public void setBlocked(boolean blocked){
		if(blocked){
			System.out.println("Alert has been blocked");
		} else {
			System.out.println("Alert has been unblocked");
		}
		this.blockedStatus = blocked;
	}
	//Checking the status of the alerts.
	public boolean isBlocked(){
		return blockedStatus;
	}
	//Setting the methods for when the appropriate events happen.
	public void badRequest(){
		System.out.println("Bad request recorded at: " + System.currentTimeMillis());
	}
	public void goodRequest(){
		System.out.println("Good request recorded at: " + System.currentTimeMillis());
	}
	public void goodResponse(){
		System.out.println("Good response recorded at: " + System.currentTimeMillis());
	}
	public void addAlert(){
		System.out.println("Alerts added: " + System.currentTimeMillis());
	}
}
