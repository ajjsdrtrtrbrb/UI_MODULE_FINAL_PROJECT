package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.forms.LoginForm;
import org.pages.forms.RegistrationForm;
import org.utils.ConfigProvider;

public class AuthPage extends ParentPage {
    @FindBy(xpath = "//span[text()='SIGN IN']")
    private WebElement signInButton;
    @FindBy(xpath = "//a[contains(@class,'header__signup')]")
    private WebElement sighUpButton;

    public AuthPage(WebDriver webDriver) {
        super(webDriver);
    }


   public AuthPage openAuthPage() {
       webDriver.get(ConfigProvider.configProperties.base_url());
       logger.info("Auth page was opened with ull "+ConfigProvider.configProperties.base_url());
       return this;
   }

    public RegistrationForm openRegistrationForm() {
        clickOnElement(sighUpButton, "Sign Up button");
        return new RegistrationForm(webDriver);
    }

    public LoginForm openLoginForm() {
        clickOnElement(signInButton, "Sign In button");
        return new LoginForm(webDriver);
    }
    public AuthPage checkSignInButtonIsVisible(){
        checkIsElementVisible(signInButton,"Sign In Button");
        return this;
    }
    public AuthPage checkSignUpButtonIsVisible(){
        checkIsElementVisible(sighUpButton,"Sign Up Button");
        return this;
    }
}
