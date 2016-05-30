Feature: Gmail spam filter checks

  Scenario: Mark email as spam
    Given I received mail from "SeleniumTest00100" with password "SeleniumTest001" to "SeleniumTest00200@gmail.com" with "TestSpamSubjText1" and "TestSpamBodyText1"
    When I login as "SeleniumTest00200" with password "SeleniumTest002" and mark received mail as spam
    Then I as "SeleniumTest00200" with password "SeleniumTest002" can see mail with "TestSpamSubjText1" in Spam folder


  Scenario: Second email from spammer appears in Spam folder automatically
    Given I received mail from "SeleniumTest00100" with password "SeleniumTest001" to "SeleniumTest00200@gmail.com" with "TestSpamSubjText2" and "TestSpamBodyText2"
    And I login as "SeleniumTest00200" with password "SeleniumTest002" and mark received mail as spam
    When I received mail from "SeleniumTest00100" with password "SeleniumTest001" to "SeleniumTest00200@gmail.com" with "TestSpamSubjText3" and "TestSpamBodyText3"
    Then I as "SeleniumTest00200" with password "SeleniumTest002" can see mail with "TestSpamSubjText3" in Spam folder

