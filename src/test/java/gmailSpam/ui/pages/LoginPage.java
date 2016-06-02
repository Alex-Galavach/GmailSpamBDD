package gmailSpam.ui.pages;

import gmailSpam.webdriver.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "Email")
    private WebElement inputLogin;

    @FindBy(id = "next")
    private WebElement buttonNext;

    @FindBy(id = "Passwd")
    private WebElement inputPassword;

    @FindBy(id = "signIn")
    private WebElement buttonSignIn;

    public static final String Login_URL = "https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier";

    Logger logger = LogManager.getRootLogger();

    public LoginPage() {
        PageFactory.initElements(Browser.getInstance(), this);
    }

    public void openLoginPage() {
        Browser.getInstance().manage().deleteAllCookies();
        Browser.getInstance().get(Login_URL);
        logger.info(Login_URL + " is opened");
    }

    public InboxPage signIn(String username, String password) {
        inputLogin.sendKeys(username);
        buttonNext.click();
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        logger.info(username + " is logged in");
        return new InboxPage();
    }
}
