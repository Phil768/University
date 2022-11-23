package test.calculator;

import edu.cps3230.calculator.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class CalculatorSteps {

    Calculator calculator;

    @Given("I am using the calculator")
    public void iAmUsingTheCalculator() {
        calculator = new Calculator();
    }
    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calculator.add(arg0, arg1);
    }
    @When("I subtract {int} from {int}")
    public void iSubtractFrom(int arg0, int arg1) {
        calculator.subtract(arg1, arg0);
    }
    @When("I divide {int} by {int}")
    public void iDivideBy(int arg0, int arg1) {
        calculator.division(arg0, arg1);
    }
    @When("I multiply {int} by {int}")
    public void iMultiplyBy(int arg0, int arg1) {
        calculator.multiplication(arg0, arg1);
    }
    @Then("I should get the added result of {int}")
    public void iShouldGetTheAddedResultOf(int arg0) {
        Assertions.assertEquals(arg0, calculator.additionResult);
    }
    @Then("I should get the difference result of {int}")
    public void iShouldGetTheDifferenceResultOf(int arg0) {
        Assertions.assertEquals(arg0, calculator.subtractionResult);
    }
    @Then("I should get the divided result of {int}")
    public void iShouldGetTheDividedResultOf(int arg0) {
        Assertions.assertEquals(arg0, calculator.divisionResult);
    }
    @Then("I should get the multiplied result of {int}")
    public void iShouldGetTheMultipliedResultOf(int arg0) {
        Assertions.assertEquals(arg0, calculator.multiplicationResult);
    }
}
