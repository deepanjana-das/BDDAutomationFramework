package stepdefinitions;

import common.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logics.common.Core;
import org.openqa.selenium.WebDriver;

public class TableOperation {
   // private ScenarioContext scenarioContext;

    public ScenarioContext scenarioContext = new ScenarioContext();
    public Core core = new Core();

  /*  public TableOperation(){
        scenarioContext = new ScenarioContext();
    }*/

    @Given("Open browser and go to web page url {string}")
    public void gotoWebPage(String url){
        WebDriver driver = core.gotoWebPage(url);
        scenarioContext.setContext("driver", driver);
    }

    @When("First table elements are present")
    public void checkFirstTablePresence(){
        if(scenarioContext.isContains("driver")){
            WebDriver driver = (WebDriver) scenarioContext.getContext("driver");

            /* TODO: */
        }
    }

    @Then("Read all row and col values from that table")
    public void readTableData(){
        if(scenarioContext.isContains("driver")){
            WebDriver driver = (WebDriver) scenarioContext.getContext("driver");

            /* TODO: */
        }
    }
}
