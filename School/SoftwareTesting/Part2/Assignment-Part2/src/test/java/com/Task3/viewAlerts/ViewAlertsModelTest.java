package com.Task3.viewAlerts;

import com.Task3.viewAlerts.enums.ViewStateEnum;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Random;

public class ViewAlertsModelTest implements FsmModel {
    //Create a new System under test object.
    viewAlertsOperator sut = new viewAlertsOperator();
    //Initializing the starting state of the system.
    ViewStateEnum stateEnum = ViewStateEnum.MARKETALERTUM;
    //Initializing all the necessary variables.
    boolean loginRequest = false;
    boolean badLogin = false;
    boolean goodLogin = false;
    boolean viewRequest = false;

    @Override
    public ViewStateEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean b) {
        if(b) {
            sut = new viewAlertsOperator();
        }
        loginRequest = false;
        badLogin = false;
        goodLogin = false;
        viewRequest = false;
        stateEnum = ViewStateEnum.MARKETALERTUM;
    }

    public boolean loginRequestGuard(){return getState().equals(ViewStateEnum.MARKETALERTUM) || getState().equals(ViewStateEnum.BLOCKED);}
    public @Action void loginRequest(){
        sut.sendLoginRequest();

        loginRequest = true;
        stateEnum = ViewStateEnum.LOGIN;

        Assert.assertEquals("The model's login state doesn't match the System-under-test's state", loginRequest, sut.isInLogin());
    }

    public boolean setBadLoginGuard(){return getState().equals(ViewStateEnum.LOGIN);}
    public @Action void setBadLogin(){
        sut.setBadLogin();

        badLogin = true;
        loginRequest = false;
        stateEnum = ViewStateEnum.BLOCKED;

        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", badLogin, sut.isInBlocked());
    }

    public boolean setGoodLoginGuard(){return getState().equals(ViewStateEnum.LOGIN);}
    public @Action void setGoodLogin(){
        sut.setGoodLogin();

        goodLogin = true;
        badLogin = false;
        stateEnum = ViewStateEnum.ALERTS;

        Assert.assertEquals("The model's Alert state doesn't match the System-under-test's state", goodLogin, sut.isInAlerts());/*CHECK*/
        Assert.assertEquals("The model's bad login state doesn't match the System-under-test's state", badLogin, sut.isInAlerts());
    }

    public boolean viewAlertsGuard(){return getState().equals(ViewStateEnum.ALERTS);}
    public @Action void viewAlerts(){
        sut.viewAlert();

        viewRequest = true;
        stateEnum = ViewStateEnum.ALERTS;

        Assert.assertEquals("The model's Alert state doesn't match the System-under-test's state", viewRequest, sut.isInAlerts());

    }

    @Test
    public void sendAlertRunner() throws FileNotFoundException {
        final Tester tester = new GreedyTester(new ViewAlertsModelTest());
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
