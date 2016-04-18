Feature: Gmail spam filter checks

  Scenario: Mark email as spam
    Given I received mail from "Alex.Galavach" with password "RadaYana" to "Alexander.Galavach@gmail.com" with "TestSpamSubjText1" and "TestSpamBodyText1"
    When I login as "Alexander.Galavach" with password "RadaYana" and mark received mail as spam
    Then I as "Alexander.Galavach" with password "RadaYana" can see mail with "TestSpamSubjText1" in Spam folder


  Scenario: Second email from spammer appears in Spam folder automatically
    Given I received mail from "Alex.Galavach" with password "RadaYana" to "Alexander.Galavach@gmail.com" with "TestSpamSubjText2" and "TestSpamBodyText2"
    And I login as "Alexander.Galavach" with password "RadaYana" and mark received mail as spam
    When I received mail from "Alex.Galavach" with password "RadaYana" to "Alexander.Galavach@gmail.com" with "TestSpamSubjText3" and "TestSpamBodyText3"
    Then I as "Alexander.Galavach" with password "RadaYana" can see mail with "TestSpamSubjText3" in Spam folder

