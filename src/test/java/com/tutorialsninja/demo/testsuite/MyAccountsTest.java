package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.AccountLoginPage;
import com.tutorialsninja.demo.pages.AccountRegisterPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyAccountsTest extends BaseTest {
    HomePage homepage;
    AccountRegisterPage registerPage;
    AccountLoginPage accountLoginPage;

    @BeforeMethod(alwaysRun = true)
    public void intIt() {
        homepage = new HomePage();
        registerPage = new AccountRegisterPage();
        accountLoginPage = new AccountLoginPage();
    }

    //1.1 create method with name "selectMyAccountOptions" it has one parameter name "option" of type string
    public void selectMyAccountOptions(String option) {
        accountLoginPage.selectOption(option);
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    // 1.	Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        // * 1.1	Click on My Account Link.
        homepage.clickOnAccountLink();

        // * 1.2	Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

        // * 1.3	Verify the text “Register Account”.
        registerPage.verifyRegisterAccountText();
    }

    @Test(groups = {"smoke", "regression"})
    //2.	Test name verifyUserShouldNavigateToLoginPageSuccessfully()
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        // * 2.1	Click on My Account Link.
        homepage.clickOnAccountLink();
        // * 2.2	Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        // * 2.3	Verify the text “Returning Customer”.
        registerPage.verifyTextReturningCustomerOnLoginPage();
    }

    @Test(groups = {"regression"})
    public void verifyThatUserRegisterAccountSuccessfully() {
        // * 3.1	Click on My Account Link.
        homepage.clickOnAccountLink();

        // * 3.2	Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

        // * 3.3	Enter First Name
        registerPage.enterFirstName("Mina");

        // * 3.4	Enter Last Name
        registerPage.enterLastName("Joshi");

        // * 3.5	Enter Email
        final String randomEmail;
        randomEmail = AccountRegisterPage.randomEmail();
        registerPage.enterEmail(randomEmail);

        // * 3.6	Enter Telephone
        registerPage.enterTelephoneNumber("09876543212");

        // * 3.7	Enter Password
        registerPage.enterPassword("0987654");

        // * 3.8	Enter Password Confirm
        registerPage.enterConfirmPassword("0987654");

        // * 3.9	Select Subscribe Yes radio button
        registerPage.clickOnSubscribeRadioButton();

        // * 3.10	Click on Privacy Policy check box
        registerPage.clickOnPrivacyPolicyCheckBox();

        // * 3.11	Click on Continue button
        registerPage.clickOnContinueButton();

        // * 3.12	Verify the message “Your Account Has Been Created!”
        String actualMessage = accountLoginPage.verifyTheMessageYourAccountHasBeenCreated();
        String expectedMessage = "Your Account Has Been Created!";
        Assert.assertEquals(actualMessage, expectedMessage, "Incorrect message displayed!");

        // * 3.13	Click on Continue button
        accountLoginPage.clickOnContinueAccountButton();

        // 3.14 Click on My Account Link.
        accountLoginPage.clickOnMyAccountLink();

        // * 3.15	Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");

        // * 3.16	Verify the text “Account Logout”
        String actualLogoutText = accountLoginPage.verifyTextForAccountLogOut();
        String expectedLogOutText = "Logout";
        Assert.assertEquals(actualLogoutText, expectedLogOutText, "Incorrect logout message displayed!");

        // * 3.17	Click on Continue button
        accountLoginPage.clickOnContinueLogOutButton();
    }

    @Test(groups = {"regression"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        // * 4.1	Click on My Account Link.
        homepage.clickOnAccountLink();

        // * 4.2	Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

        // * 4.3	Enter Email address
        registerPage.enterEmail("robert_jackson@gmail.com");

        // * 4.5	Enter Password
        registerPage.enterPassword("12345678");

        // * 4.6	Click on Login button
        accountLoginPage.clickOnLoginButton();

        // * 4.7	Verify text “My Account”
        String actualText = accountLoginPage.verifyTheTextMyAccount();
        String expectedText = "My Account";
        Assert.assertEquals(actualText, expectedText, "Incorrect my account text displayed!");

        // 4.8  Click on My Account Link.
        accountLoginPage.clickOnMyAccountLink();

        // * 4.9	Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");

        // * 4.10	Verify the text “Account Logout”
        String actualLogoutText = accountLoginPage.verifyTextForAccountLogOut();
        String expectedLogOutText = "Logout";
        Assert.assertEquals(actualLogoutText, expectedLogOutText, "Incorrect logout message displayed!");

        // * 4.11	Click on Continue button
        accountLoginPage.clickOnContinueLogOutButton();
    }

}
