package org.pages.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ParentPage;

public class ForgotPasswordForm extends ParentPage {
    @FindBy(xpath = "//div[@class=\"header-title__name\"]//span[contains(text(),'RESET YOUR PASSWORD')]")
    private WebElement resetPasswordForm;
    @FindBy(xpath = "//input[@id='MULTICHANNEL']")
    private WebElement inputResetPassword;
    public ForgotPasswordForm(WebDriver webDriver) {
        super(webDriver);
    }

    public ForgotPasswordForm checkResetPasswordFormIsVisible(){
        checkIsElementVisible(resetPasswordForm,"resetPasswordForm");
        return this;
    }
    public ForgotPasswordForm checkEmailForResetPasswordIsVisible(){
       checkIsElementVisible(inputResetPassword,"inputResetPassword");
        return this;
    }


}
