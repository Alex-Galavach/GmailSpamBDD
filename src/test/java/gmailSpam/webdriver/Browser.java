package gmailSpam.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// Singletone pattern example
public class Browser {

    public static WebDriver driver;

    //private Browser (){};

    public static WebDriver getInstance(){
        if (driver==null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            return driver;
        }
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        //driver = null; //commented to avoid warnings on Commit
    }
}

