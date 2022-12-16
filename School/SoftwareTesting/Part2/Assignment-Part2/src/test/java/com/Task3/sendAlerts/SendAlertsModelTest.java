package com.Task3.sendAlerts;

import com.Task3.sendAlerts.enums.SendStateEnum;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Random;

public class SendAlertsModelTest implements FsmModel {
    //Create a new System under test object.
    sendAlertsOperator sut = new sendAlertsOperator();
    //Initializing the starting state of the system.
    SendStateEnum stateEnum = SendStateEnum.SCRAPER;
    //Initializing all the necessary variables.
    boolean scraping = true;
    boolean badRequest = false;
    boolean goodAlert = false;
    int alerts = 0;

    //Getting the current state of the FSM.
    @Override
    public SendStateEnum getState() {
        return stateEnum;
    }
    //Resetting to the initial states of the FSM.
    @Override
    public void reset(boolean b) {
        if(b) {
            sut = new sendAlertsOperator();
        }
        scraping = true;
        badRequest = false;
        goodAlert = false;
        alerts = 0;
        stateEnum = SendStateEnum.SCRAPER;
    }

    public boolean badApiRequestGuard(){return getState().equals(SendStateEnum.SCRAPER);}
    public @Action void badApiRequest(){
        //Sending a bad request.
        sut.sendBadApiRequest();

        badRequest = true;
        scraping = false;
        stateEnum = SendStateEnum.BAD_STATE;
        //Testing that we have moved from the scraper to the bad state.
        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", badRequest, sut.isInBadState());
        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", scraping, sut.isInScraper());
    }

    public boolean maximumAlertsGuard(){return getState().equals(SendStateEnum.SCRAPER);}
    public @Action void maximumAlerts(){
        //Setting the number alerts to exceed the maximum.
        sut.maximumAlerts();

        badRequest = true;
        scraping = false;
        stateEnum = SendStateEnum.BAD_STATE;
        //Testing that we have moved from the scraper to the bad state.
        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", badRequest, sut.isInBadState());
        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", scraping, sut.isInScraper());
    }

    public boolean returnToScraperGuard(){return getState().equals(SendStateEnum.BAD_STATE);}
    public @Action void returnToScraper(){
        //Retry sending the request.,
        sut.Retry();

        badRequest = false;
        scraping = true;
        stateEnum = SendStateEnum.SCRAPER;
        //Testing that we have moved from the bad state back to the scraper.
        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", badRequest, sut.isInBadState());
        Assert.assertEquals("The model's scraper state doesn't match the System-under-test's state", scraping, sut.isInScraper());
    }

    public boolean goodApiRequestGuard(){return getState().equals(SendStateEnum.SCRAPER);}
    public @Action void goodApiRequest(){
        //Sending a good request.
        sut.sendGoodApiRequest();

        goodAlert = true;
        scraping = false;
        stateEnum = SendStateEnum.MARKET_ALERT_UM;
        //Testing that we have moved from the scraper to the market alert site.
        Assert.assertEquals("The model's Market Alert state doesn't match the System-under-test's state", goodAlert, sut.isInMarketAlert());
        Assert.assertEquals("The model's scraper state doesn't match the System-under-test's state", scraping, sut.isInScraper());
    }


    public boolean responseGuard(){return getState().equals(SendStateEnum.MARKET_ALERT_UM);}
    public @Action void response(){
        //Sending a response back.
        sut.sendGoodResponse();

        goodAlert = false;
        scraping = true;
        stateEnum = SendStateEnum.SCRAPER;
        //Testing that we have moved back to the scraper from the market alert site.
        Assert.assertEquals("The model's scraper state doesn't match the System-under-test's state", scraping, sut.isInScraper());
        Assert.assertEquals("The model's Market Alert state doesn't match the System-under-test's state", goodAlert, sut.isInMarketAlert());

    }

    //Test runner
    @Test
    public void SendAlertsModelRunner() {
        final GreedyTester tester = new GreedyTester(new SendAlertsModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have been executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }

}
