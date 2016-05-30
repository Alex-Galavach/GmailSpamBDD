package gmailSpam.utilities;

import gmailSpam.webdriver.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class PageHighlightAndScreenShot {
	public static void takeScreenShot(String label){
		//ChromeDriver driver = (ChromeDriver) Browser.getInstance();
		//File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        ChromeDriver driver = (ChromeDriver) Browser.getInstance();
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			String fileName = label+".png";
			FileUtils.copyFile(screenshotFile, new File (fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void highlightAreaAndScreenShot(WebDriver driver, WebElement element){
		JavascriptExecutor jsexecutor = ((JavascriptExecutor)driver);
		String color = element.getCssValue("backgroundColor");
		jsexecutor.executeScript("arguments[0].style.backgroundColor = '"+ "yellow"+"'", element);
		takeScreenShot("highlight");
		jsexecutor.executeScript("arguments[0].style.backgroundColor = '"+ color + "'", element);
		//takeScreenShot("afterhighlight");
	}

}
