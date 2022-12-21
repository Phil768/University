package com.Task3.viewAlerts;

public class viewAlertsOperator {
    private boolean loginRequest = false;//States that we are in the LOGIN state.
    private boolean unlockLogin = false;//States that we are back in the LOGIN state.
    private boolean lockLogin = false;//States that we are in the BLOCKED state.
    private boolean goodLogin = false;//States that a good login has been recorded.
    private boolean viewRequest = false;//States that we are in the ALERTS state.
    private boolean loggedOut = false;//States that a log-out has been recorded.
    //Sending a login request from the market alert site.
    void sendLoginRequest(){
        //Checking that there are no other requests pending.
        if(!loginRequest){
            //Stating that we are in the LOGIN state.
            loginRequest = true;
        }else {
            throw new IllegalStateException();
        }
    }
    //Locking the system if the LOGIN is bad and moving to the BLOCKED state.
    void setLockLogin(){
        //Checking that there has been a login request.
        if(loginRequest && !goodLogin){
            //Locking the login and moving to the BLOCKED state.
            lockLogin = true;
            //Stating that we are no longer in the LOGIN state.
            loginRequest = false;
            //Stating that a good login has been attempted, but it has failed.
            goodLogin = false;
        }else {
            throw new IllegalStateException();
        }
    }
    //Unlocking the login to allow the system to move forward.
    void setUnlockLogin(){
        //Checking if the login has been locked before attempting to unlock it.
        if(lockLogin){
            //Stating that we are back in the LOGIN state.
            unlockLogin = true;
            //Stating that a new login request has been created.
            loginRequest = true;
            //Stating that the login is no longer locked, and we have moved from the BLOCKED state.
            lockLogin = false;
        }else {
            throw new IllegalStateException();
        }
    }
    //Performing a good login to be able to use the system.
    void setGoodLogin(){
        //Checking if login is available.
        if((unlockLogin || loginRequest) && !goodLogin){
            goodLogin = true;
            loginRequest =false;
        }else {
            throw new IllegalStateException();
        }
    }
    //Viewing the alerts. (GOAL)
    void viewAlert(){
        //Checking if there has been a good login.
        if(goodLogin){
            //Stating that we are in the ALERTS state.
            viewRequest = true;
        }else {
            throw new IllegalStateException();
        }
    }
    void setLogOut(){
        //Checking that the system is logged in before attempting to log out.
        if(goodLogin || viewRequest){
            loggedOut = true;
            goodLogin = false;
            viewRequest = false;
        }else {
            throw new IllegalStateException();
        }
    }
    /*Methods to determine the current location of the SUT.*/
    boolean isInBlocked(){ return lockLogin;}//Checking if we are in the BLOCKED state.
    boolean isInLogin(){return loginRequest;}//Checking if we are in the LOGIN state.
    boolean isGoodLogin(){return goodLogin;}//Checking if a good login has been recorded before moving to the alerts.
    boolean isInAlerts(){return viewRequest;}//Checking if we are in the ALERTS state.
    boolean isLoggedOut(){return loggedOut;}//Checking if we have logged out of the system.

}
