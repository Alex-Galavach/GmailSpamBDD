package gmailSpam.ui.pages;

import gmailSpam.utilities.PageHighlightAndScreenShot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage {

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private WebElement emailNew;

    @FindBy(name = "to")
    private WebElement emailReceiverName;

    @FindBy(name = "subjectbox")
    private WebElement emailSubject;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement emailBody;

    @FindBy(xpath = "//div[contains(@data-tooltip, 'Send')]")
    private WebElement emailSendButton;

    @FindBy(xpath = ".//div[@role='tabpanel']//*[@role='checkbox']")
    private static WebElement selectFirstMail;

    @FindBy(xpath = ".//div[@class='asl T-I-J3 J-J5-Ji']")
    private static WebElement markAsSpam;

    @FindBy(xpath = ".//div[@role='banner']//*[contains(@title,'@gmail.com')]")
    private static WebElement logoutIcon;

    @FindBy(xpath = ".//*[@id='gb_71']")
    private static WebElement logoutBotton;

    public static final String Inbox_URL = "https://mail.google.com/mail/#inbox";

    public static final String Spam_URL = "https://mail.google.com/mail/#spam";


	private WebDriver driver;

    Logger logger = LogManager.getRootLogger();

	public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void SendEmail(String emailReceiverNameText, String emailSubjectText, String emailBodyText){

        emailNew.click();
        logger.info("New email form opened");
        emailReceiverName.sendKeys(emailReceiverNameText);
        logger.info(emailReceiverNameText + " set in TO");
        PageHighlightAndScreenShot.highlightAreaAndScreenShot(driver, emailSubject);
        emailSubject.sendKeys(emailSubjectText);
        PageHighlightAndScreenShot.highlightAreaAndScreenShot(driver, emailSubject);
        logger.info(emailSubjectText + " set in Subject");
        PageHighlightAndScreenShot.highlightAreaAndScreenShot(driver, emailBody);
        emailBody.sendKeys(emailBodyText);
        PageHighlightAndScreenShot.highlightAreaAndScreenShot(driver, emailBody);
        logger.info(emailBodyText + " set in mail body");
        emailSendButton.click();
        logger.info("Mail sent");
    }

    public void PageRefresh(){
        driver.navigate().refresh();
    }

    public void alertIgnore() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch (Exception ignored) {}
    }

    public void MailToSpam(){
        PageRefresh();
        //wait.until(ExpectedConditions.elementToBeClickable(selectFirstMail)); - looks like not needed anymore
        selectFirstMail.click();
        markAsSpam.click();
    }

    public void Logout() {
        logoutIcon.click();
        logoutBotton.click();
        alertIgnore();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        //driver.navigate().refresh(); //- looks like not needed anymore
    }

    public void openSpamPage() {
        driver.get(Spam_URL);
    }

    public Boolean emailPresenceCheck (WebDriver driver, String subjectText) {
        openSpamPage();

        try {
            driver.findElement(By.xpath(String.format(
                    "//*[@role='main']/div/div/div/table/tbody/tr/td/div/div/div/span/b[contains(., '%s')]",
                    subjectText))).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
