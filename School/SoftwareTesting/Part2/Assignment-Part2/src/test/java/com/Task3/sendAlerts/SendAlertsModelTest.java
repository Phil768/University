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
    boolean retry = false;
    boolean goodResponse = false;

    @Override
    public SendStateEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean b) {
        if(b) {
            sut = new sendAlertsOperator();
        }
        scraping = true;
        badRequest = false;
        goodAlert = false;
        retry = false;
        goodResponse = false;
        stateEnum = SendStateEnum.SCRAPER;
    }

    public boolean BadApiRequestGuard(){return getState().equals(SendStateEnum.SCRAPER);}
    public @Action void BadApiRequest(){
        sut.sendBadApiRequest();

        badRequest = true;
        stateEnum = SendStateEnum.BADSTATE;

        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", badRequest, sut.isInBadState());
    }

    public boolean returnToScraperGuard(){return getState().equals(SendStateEnum.BADSTATE);}
    public @Action void returnToScraper(){
        sut.Retry();

        badRequest = false;
        retry = true;
        scraping = true;/*CHECK*/
        stateEnum = SendStateEnum.SCRAPER;

        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", badRequest, sut.isInBadState());
        Assert.assertEquals("The model's scraper state doesn't match the System-under-test's state", retry, sut.isInScraper());
    }

    public boolean goodApiRequestGuard(){return getState().equals(SendStateEnum.SCRAPER);}
    public @Action void goodApiRequest(){
        sut.sendGoodApiRequest();

        goodAlert = true;
        scraping = false;
        stateEnum = SendStateEnum.MARKETALERTUM;

        Assert.assertEquals("The model's Market Alert state doesn't match the System-under-test's state", badRequest, sut.isInMarketAlert());
        Assert.assertEquals("The model's scraper state doesn't match the System-under-test's state", scraping, sut.isInScraper());
    }

    public boolean responseGuard(){return getState().equals(SendStateEnum.MARKETALERTUM);}
    public @Action void response(){
        sut.sendGoodResponse();

        goodAlert = false;
        goodResponse = true;
        scraping = true;/*CHECK*/
        stateEnum = SendStateEnum.SCRAPER;

        Assert.assertEquals("The model's scraper state doesn't match the System-under-test's state", scraping, sut.isInScraper());
        Assert.assertEquals("The model's Market Alert state doesn't match the System-under-test's state", goodAlert, sut.isInMarketAlert());

    }

    @Test
    public void sendAlertRunner() throws FileNotFoundException {
        final Tester tester = new GreedyTester(new SendAlertsModelTest());
        tester.setRandom(new Random());
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(250);
        tester.printCoverage();
    }

}
