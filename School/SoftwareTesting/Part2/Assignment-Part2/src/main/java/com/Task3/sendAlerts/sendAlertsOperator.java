package com.Task3.sendAlerts;

public class sendAlertsOperator {
    private boolean scraping = true;
    private boolean badRequest = false;
    private boolean goodAlert = false;
    private boolean retry = false;
    private boolean goodResponse = false;

    void sendBadApiRequest(){
        //Checking that we are not in any other state.
        if (!(badRequest || goodAlert)) {
            badRequest = true;
        } else {
            throw new IllegalStateException();
        }
    }

    void Retry() {
        if(badRequest){
           retry = true;
           scraping = true;
           badRequest = false;
        }else {
            throw new IllegalStateException();
        }
    }

    void sendGoodApiRequest(){
        if (!badRequest && retry) {
            goodAlert = true;
            scraping = false;
        } else {
            throw new IllegalStateException();
        }
    }

    void sendGoodResponse(){
        if(goodAlert){
            goodResponse = true;
            scraping = true;
        }else {
            throw new IllegalStateException();
        }
    }

    boolean isInScraper() {return scraping;}
    boolean isInBadState(){ return badRequest;}
    boolean isInMarketAlert(){ return goodAlert;}
}

