package com.Task3.viewAlerts;

public class viewAlertsOperator {
    private boolean loginRequest = false;
    private boolean badLogin = false;
    private boolean goodLogin = false;
    private boolean viewRequest = false;

    void sendLoginRequest(){
        if(!(loginRequest || badLogin || viewRequest)){
            loginRequest = true;
        }
    }

    void setBadLogin(){
        if(loginRequest){
            badLogin = true;
            loginRequest = false;
        }
    }

    void setGoodLogin(){
        if(badLogin){
            goodLogin = true;
            badLogin = false;
        }
    }

    void viewAlert(){
        if(goodLogin){
            viewRequest = true;
        }
    }

    boolean isInMarketAlert(){return !loginRequest;}
    boolean isInBlocked(){ return badLogin;}
    boolean isInLogin(){return loginRequest;}
    boolean isInAlerts(){return viewRequest || goodLogin;}

}
