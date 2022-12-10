package com.Task3.viewAlerts;

public class viewAlertsOperator {
    private boolean loginRequest = false;//States that we are in the LOGIN state.
    private boolean unlockLogin = false;//States that we are back in the LOGIN state.
    private boolean lockLogin = false;//States that we are in the BLOCKED state.
    private boolean viewRequest = false;//States that we are in the ALERTS state.
    //Sending a login request from the market alert site.
    void sendLoginRequest(){
        //Checking that there are no other requests pending.
        if(!(loginRequest || viewRequest)){
            //Stating that we are in the LOGIN state.
            loginRequest = true;
        }else {
            throw new IllegalStateException();
        }
    }
    //Locking the system if the LOGIN is bad and moving to the BLOCKED state.
    void setLockLogin(){
        //Checking that there has been a login request.
        if(loginRequest){
            //Locking the login and moving to the BLOCKED state.
            lockLogin = true;
            //Stating that we are no longer in the LOGIN state.
            loginRequest = false;
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
    //Viewing the alerts. (GOAL)
    void viewAlert(){
        //Checking if there has been a login request or that the login has been unlocked.
        if(unlockLogin || loginRequest){
            //Stating that we are in the ALERTS state.
            viewRequest = true;
        }else {
            throw new IllegalStateException();
        }
    }
    /*Methods to determine the current location of the SUT.*/
    boolean isInBlocked(){ return lockLogin;}//Checking if we are in the BLOCKED state.
    boolean isInLogin(){return loginRequest;}//Checking if we are in the LOGIN state.
    boolean isInAlerts(){return viewRequest;}//Checking if we are in the ALERTS state.

}
