package org.tests.RegistrationLoginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.pages.forms.LoginForm;


public class LoginTests extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.getAuthPage()
                .openAuthPage()
                .openLoginForm()
                .loginFormIsVisible()
                .enterLogin(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickOnSubmitButton();
        pageProvider.getHomePage()
                .openMyProfileDropDown()
                .getHeader()
                .checkLogoutIsVisible();

    }


    @Test
    public void invalidLogin() {
        LoginForm loginForm = pageProvider.getAuthPage()
                .openAuthPage()
                .openLoginForm()
                .loginFormIsVisible()
                .enterLogin(TestData.INVALID_LOGIN)
                .enterPassword(TestData.INVALID_PASSWORD)
                .clickOnSubmitButton();


        loginForm.loginFormIsVisible();

        loginForm.errorMessageIsVisible();

        String expectedError = "Username or password is incorrect. Please, try again";
        Assert.assertEquals("Error message text is incorrect", expectedError, loginForm.getErrorMessage());
    }

    @Test
    public void logOutWithConfirmation() {
        pageProvider.getAuthPage()
                .openAuthPage()
                .openLoginForm()
                .loginFormIsVisible()
                .enterLogin(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickOnSubmitButton();
        pageProvider.getHomePage()
                .openMyProfileDropDown()
                .getHeader()
                .checkLogoutIsVisible();
        pageProvider.getHomePage()
                .getHeader()
                .clickLogout()
                .checkLogoutConfirmationModalIsVisible()
                .confirmLogout()
                .checkSignInButtonIsVisible()
                .checkSignUpButtonIsVisible();

    }
    @Test
    public void logOutWithCancel(){
        pageProvider.getAuthPage()
                .openAuthPage()
                .openLoginForm()
                .loginFormIsVisible()
                .enterLogin(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickOnSubmitButton();
        pageProvider.getHomePage()
                .openMyProfileDropDown()
                .getHeader()
                .clickLogout()
                .checkLogoutConfirmationModalIsVisible()
                .cancelLogout()
                .checkDepositButtonIsVisible()
                .openMyProfile()
                .checkLogoutIsVisible();
    }
    @Test
    public void checkForgotPasswordFormIsOpened(){
        pageProvider.getAuthPage()
                .openAuthPage()
                .openLoginForm()
                .loginFormIsVisible()
                .clickOnForgotPasswordButton()
                .checkResetPasswordFormIsVisible()
                .checkEmailForResetPasswordIsVisible();
    }
}
