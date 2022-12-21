package com.Task3.viewAlerts;

import com.Task3.viewAlerts.enums.ViewStateEnum;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class ViewAlertsModelTest implements FsmModel {
    //Create a new System under test object.
    viewAlertsOperator sut = new viewAlertsOperator();
    //Initializing the starting state of the system.
    ViewStateEnum stateEnum = ViewStateEnum.MARKET_ALERT_UM;
    //Initializing all the necessary variables.
    boolean loginRequest = false;
    boolean lockLogin = false;
    boolean unlockLogin = false;
    boolean goodLogin = false;
    boolean viewRequest = false;
    boolean logOut = false;
    //Getting the current state of the FSM.
    @Override
    public ViewStateEnum getState() {
        return stateEnum;
    }
    //Resetting the FSM to its initial states.
    @Override
    public void reset(boolean b) {
        if(b) {
            sut = new viewAlertsOperator();
        }
        loginRequest = false;
        unlockLogin = false;
        lockLogin = false;
        goodLogin = false;
        viewRequest = false;
        logOut = false;
        stateEnum = ViewStateEnum.MARKET_ALERT_UM;
    }

    public boolean loginRequestGuard(){return getState().equals(ViewStateEnum.MARKET_ALERT_UM);}
    public @Action void loginRequest(){
        //Sending a login request.
        sut.sendLoginRequest();

        loginRequest = true;
        stateEnum = ViewStateEnum.LOGIN;
        //Testing that we have moved from the MARKET_ALERT_UM to the LOGIN state.
        Assert.assertEquals("The model's login state doesn't match the System-under-test's state", loginRequest, sut.isInLogin());
    }

    public boolean setLockLoginGuard(){return getState().equals(ViewStateEnum.LOGIN);}
    public @Action void setLockLogin(){
        //Checking if a good login has been recorded since if this is the case the system should not be able to lock once again.
        if(!sut.isGoodLogin()) {
            //Locking the system.
            sut.setLockLogin();

            loginRequest = false;
            lockLogin = true;
            stateEnum = ViewStateEnum.BLOCKED;
            //Testing that we have moved from the LOGIN state to the BLOCKED state.
            Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", lockLogin, sut.isInBlocked());
            Assert.assertEquals("The model's Login state doesn't match the System-under-test's state", loginRequest, sut.isInLogin());
        }
        else{
            goodLogin = true;
            stateEnum = ViewStateEnum.LOGIN;
            //testing that we have moved from the LOGIN state to teh ALERTS state.
            Assert.assertEquals("The model's Login state doesn't match the System-under-test's state", goodLogin, sut.isGoodLogin());
        }
    }

    public boolean setUnlockLoginGuard(){return getState().equals(ViewStateEnum.BLOCKED);}
    public @Action void setUnlockLogin(){
        //Unlocking the system.
        sut.setUnlockLogin();

        unlockLogin = true;
        lockLogin = false;
        stateEnum = ViewStateEnum.LOGIN;
        //Testing that we have moved from the BLOCKED state back to the LOGIN state
        Assert.assertEquals("The model's bad state doesn't match the System-under-test's state", lockLogin, sut.isInBlocked());
        Assert.assertEquals("The model's Login state doesn't match the System-under-test's state", unlockLogin, sut.isInLogin());
    }
    public boolean setGoodLoginGuard(){return getState().equals(ViewStateEnum.LOGIN);}
    public @Action void setGoodLogin(){
        //Forcing the system to first establish a good login before proceeding to view the alerts.
        if(!sut.isGoodLogin()) {
            sut.setGoodLogin();

            goodLogin = true;
            stateEnum = ViewStateEnum.LOGIN;
            //Testing that we have remained in the LOGIN state since a good login has been recorded.
            Assert.assertEquals("The model's Login state doesn't match the System-under-test's state", goodLogin, sut.isGoodLogin());
        }else{
            //do nothing since a good login has already been established.
        }
    }

    public boolean setViewAlertsGuard(){return getState().equals(ViewStateEnum.LOGIN);}
    public @Action void setViewAlerts(){
        //Forcing the system to first establish a good login before proceeding to view the alerts.
        if(sut.isGoodLogin()) {
            //Viewing the alerts.
            sut.viewAlert();

            viewRequest = true;
            stateEnum = ViewStateEnum.ALERTS;
            //testing that we have moved from the LOGIN state to teh ALERTS state.
            Assert.assertEquals("The model's Alert state doesn't match the System-under-test's state", viewRequest, sut.isInAlerts());
        }else{
            goodLogin = false;
            stateEnum = ViewStateEnum.LOGIN;
            //testing that we have moved from the LOGIN state to teh ALERTS state.
            Assert.assertEquals("The model's Login state doesn't match the System-under-test's state", goodLogin, sut.isGoodLogin());
        }
    }

    public boolean setLogOutGuard(){return getState().equals(ViewStateEnum.LOGIN) || getState().equals(ViewStateEnum.ALERTS);}
    public @Action void setLogOut(){
        //Forcing the system to first establish a good login before proceeding to view the alerts.
        if(sut.isGoodLogin()) {
            if (getState().equals(ViewStateEnum.LOGIN)) {
                //Logging out.
                sut.setLogOut();
                logOut = true;
                goodLogin = false;
                stateEnum = ViewStateEnum.MARKET_ALERT_UM;
                //testing that we have moved from the LOGIN state to teh ALERTS state.
                Assert.assertEquals("The model's logged-out state doesn't match the System-under-test's state", logOut, sut.isLoggedOut());
                Assert.assertEquals("The model's logged-in state doesn't match the System-under-test's state", goodLogin, sut.isGoodLogin());
            } else if (getState().equals(ViewStateEnum.ALERTS)) {
                //Logging out.
                sut.setLogOut();
                logOut = true;
                viewRequest = false;
                stateEnum = ViewStateEnum.MARKET_ALERT_UM;
                //testing that we have moved from the LOGIN state to teh ALERTS state.
                Assert.assertEquals("The model's logged-out state doesn't match the System-under-test's state", logOut, sut.isLoggedOut());
                Assert.assertEquals("The model's Alerts state doesn't match the System-under-test's state", viewRequest, sut.isInAlerts());
            }
        }else{
            goodLogin = false;
            stateEnum = ViewStateEnum.LOGIN;
            //testing that we have moved from the LOGIN state to teh ALERTS state.
            Assert.assertEquals("The model's Login state doesn't match the System-under-test's state", goodLogin, sut.isGoodLogin());
        }
    }

    //Test runner
    @Test
    public void ViewAlertsModelRunner() {
        final GreedyTester tester = new GreedyTester(new ViewAlertsModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
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
