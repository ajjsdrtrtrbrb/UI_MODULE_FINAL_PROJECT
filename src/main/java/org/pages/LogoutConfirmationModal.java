package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.commonElementsForAllPages.Header;

public class LogoutConfirmationModal extends ParentPage {
    @FindBy(xpath = "//span[contains(text(),'After clicking')]")
    private   WebElement confirmLogoutModalWindow;
    @FindBy(xpath = "//span[text()='Ok']")
    private   WebElement okButton;
    @FindBy(xpath = "//span[text()='No']")
    private   WebElement noButton;

    public LogoutConfirmationModal(WebDriver webDriver) {
        super(webDriver);
    }
    public LogoutConfirmationModal checkLogoutConfirmationModalIsVisible(){
        checkIsElementVisible(confirmLogoutModalWindow);
        return this;
    }
    public AuthPage confirmLogout(){
        clickOnElement(okButton,"Ok button clicked");
        return new AuthPage(webDriver);
    }
    public Header cancelLogout(){
        clickOnElement(noButton,"No button clicked");
        return new Header(webDriver);
    }
}
