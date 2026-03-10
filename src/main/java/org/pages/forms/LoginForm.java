package org.pages.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.ParentPage;

public class LoginForm extends ParentPage {
    @FindBy(xpath = "//div[@class='popup__content']")
    private WebElement loginForm;
    @FindBy(xpath = "//input[@id='MULTICHANNEL']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@id='PASSWORD']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button//span[text()='Sign In']")
    private WebElement signInButtonInTheLoginForm;
    @FindBy(xpath = "//a[@href='/signup']//span[@class='title-bold-text link']")
    private WebElement sighUpButtonInTheLoginForm;
    @FindBy(xpath = "//span[text()='Forgot Password?']")
    private WebElement forgotPasswordButton;
    @FindBy(xpath = "//span[text()='Username or password is incorrect. Please, try again']")
    private WebElement errorMessage;

    public LoginForm(WebDriver webDriver) {
        super(webDriver);
    }


    public LoginForm enterLogin(String login) {
        clearAndEnterTextInToElement(loginInput, login);
        return this;
    }

    public LoginForm enterPassword(String password) {
        clearAndEnterTextInToElement(passwordInput, password);
        return this;
    }

    public LoginForm clickOnSubmitButton() {
        clickOnElement(signInButtonInTheLoginForm,"sign in button");
        return this;
    }
    public LoginForm loginFormIsVisible(){
        checkIsElementVisible(loginForm,"login form");
        return this;
    }

    public LoginForm errorMessageIsVisible(){
        checkIsElementVisible(errorMessage,"error message");
        return this;
    }
    public ForgotPasswordForm clickOnForgotPasswordButton(){
        clickOnElement(forgotPasswordButton,"forgotPasswordButton");
        return new ForgotPasswordForm(webDriver);
    }

    public void login(String login, String password) {
        waitUntilVisible(loginForm);
        enterLogin(login);
        enterPassword(password);
        clickOnSubmitButton();
    }




    public String getErrorMessage() {
        webDriverWait10.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
