package com.Task3.sendAlerts;

public class sendAlertsOperator {
    private boolean scraping = true;//States that we are currently in the SCRAPER state.
    private boolean badRequest = false;//States that we are currently in the BAD_STATE state.
    private boolean goodAlert = false;//States that we are currently in the MARKET_ALERT_UM state.
    private int alerts = 0;//States the number of alerts to upload to the website.(Needed to test the limit which is 5) /*Set alerts to boolean to better check?*/

    //When the SUT sends a bad API request.
    void sendBadApiRequest(){
        //Checking that we are not in any other state.
        if (!(badRequest || goodAlert) || alerts > 5) {
            //Stating that we are currently in the BAD_STATE.
            badRequest = true;
            //Stating that we have left the SCRAPER state.
            scraping = false;
        } else {
            throw new IllegalStateException();
        }
    }
    //When the SUT reverts to the SCRAPER  from the BAD_STATE.
    void Retry() {
        //Making sure that we are in a bad state before attempting to retry.
        if(badRequest){
            //Stating that we are in the SCRAPER state.
            scraping = true;
            //Stating that we are not in the bad state anymore.
            badRequest = false;
            //Resets the alerts since the process needs to start from the beginning once again.
            alerts = 0;
        }else {
            throw new IllegalStateException();
        }
    }
    //When the SUT sends more than 5 alerts to the website.
    void maximumAlerts(){
        //Checking that we are currently in the SCRAPER state.
        if(scraping){
            //Exceeding the number of maximum alerts.
            alerts = 6;
            badRequest = true;
            scraping = false;
        }
    }
    //When the SUT sends a good request to the website.
    void sendGoodApiRequest(){
        //Checking that we do mot have a bad request pending(Not crucial but can be covered).
        if (!badRequest) {
            //Defining that the number of alerts is less than the maximum.
            alerts = 4;
            //Stating that we are currently in the MARKET_ALERT_UM state.
            goodAlert = true;
            //Stating that we are not in the SCRAPER state anymore.
            scraping = false;
        } else {
            throw new IllegalStateException();
        }
    }
    //When the website sends back a good response.
    void sendGoodResponse(){
        //Checking if we are currently in the MARKET_ALERT_UM state with a good alert.
        if(goodAlert){
            //Stating that we are no longer in the MARKET_ALERT_UM state.
            goodAlert = false;
            //Stating that we are back to the SCRAPER state.
            scraping = true;
        }else {
            throw new IllegalStateException();
        }
    }
    /*Methods to check current location.*/
    boolean isInScraper() {return scraping;}//Checking if we are in the SCRAPER state.
    boolean isInBadState(){ return badRequest;}//Checking if we are in the BAD_STATE
    boolean isInMarketAlert(){ return goodAlert;}//Checking if we are in the MARKET_ALERT_UM state.
    int numOfAlerts(){ return alerts;}//Checking the number of alerts that the system has.
}

