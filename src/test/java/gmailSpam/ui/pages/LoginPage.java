package gmailSpam.ui.pages;

import org.openqa.selenium.WebDriver;
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

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        driver.manage().deleteAllCookies();
        driver.get(Login_URL);
    }

    public InboxPage signIn(String username, String password) {
        inputLogin.sendKeys(username);
        buttonNext.click();
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        return new InboxPage(driver);
    }
}
