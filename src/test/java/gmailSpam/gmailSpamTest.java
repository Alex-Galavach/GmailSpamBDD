package gmailSpam;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import gmailSpam.webdriver.Browser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {
                "pretty", "json:target/Cucumber.json",
                "html:target/cucumber-html-report"
        }
)
public class gmailSpamTest {

    protected WebDriver driver;

    @BeforeClass
    public static void initBrowser() {
        Browser.getInstance();
    }

    @AfterClass
    public static void closeSelenium() {
        Browser.closeBrowser();
    }
}