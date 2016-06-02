package gmailSpam.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gmailSpam.ui.pages.InboxPage;
import gmailSpam.ui.pages.LoginPage;
import gmailSpam.webdriver.Browser;

public class GmailSpamSteps {


    @Given("^I received mail from \"([^\"]*)\" with password \"([^\"]*)\" to \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iReceivedMailFromWithToWithAnd(String UserName, String Password, String EmailAddress2, String EmailSubject, String EmailBody) throws Throwable {
        LoginPage user = new LoginPage();
        user.openLoginPage();
        InboxPage inboxPageUser1 = user.signIn(UserName, Password);
        inboxPageUser1.SendEmail(EmailAddress2, EmailSubject, EmailBody);
        inboxPageUser1.Logout();
    }

    @When("^I login as \"([^\"]*)\" with password \"([^\"]*)\" and mark received mail as spam$")
    public void iLoginAsWithPasswordAndMarkReceivedMailAsSpam(String UserName, String Password) throws Throwable {
        LoginPage user2 = new LoginPage();
        user2.openLoginPage();
        InboxPage inboxPageUser2 = user2.signIn(UserName, Password);
        inboxPageUser2.MailToSpam();
        inboxPageUser2.Logout();
        // - not sure if ALL steps should be fully independent - TBD with mentor
    }

    @Then("^I as \"([^\"]*)\" with password \"([^\"]*)\" can see mail with \"([^\"]*)\" in Spam folder$")
    public void iAsWithPasswordCanSeeMailWithInSpamFolder(String UserName, String Password, String EmailSubject) throws Throwable {
        LoginPage user2 = new LoginPage();
        user2.openLoginPage();
        InboxPage inboxPageUser2 = user2.signIn(UserName, Password);

        if (inboxPageUser2.emailPresenceCheck(Browser.getInstance(), EmailSubject)) {
            inboxPageUser2.Logout();
            System.out.println("Mail in Spam folder as expected.");
        }
        else {
            inboxPageUser2.Logout();
            throw new IllegalArgumentException("ERROR: Mail is NOT in Spam folder as it was expected.");
        }
    }
}

