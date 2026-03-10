package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.commonElementsForAllPages.Header;

public class HomePage extends ParentPage {
    @FindBy(xpath = "//span[text()='Successful Registration']")
    private  WebElement successRegistrationForOneClickModalWindow;
    @FindBy(xpath = "//*[@href='#close']")
    private   WebElement closeButtonSuccessRegistrationForOneClickModalWindow;
    @FindBy(xpath = "//div[contains(@class,'header__account')]//button[contains(@class,'dropdown-button')]")
    private    WebElement myProfileDropDown;
    @FindBy(xpath = "//div[contains(@class,'sub-title-text') and starts-with(text(),'ID')]")
    private WebElement clientIdFromWebSite;
    @FindBy(xpath = "//span[@class='account-dropdown__text' and text()='Responsible Gaming']")
    private WebElement responsibleGamingDropdownOption;
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public Header getHeader() {
        return new Header(webDriver);
    }

    public HomePage checkIsSuccessRegistrationForOneClickModalWindowVisible(){
        checkIsElementVisible(successRegistrationForOneClickModalWindow, "SuccessRegistrationForOneClickModalWindow");
        return this;
    }
    public HomePage closeSuccessRegistrationForOneClickModalWindow(){
        clickOnElement(closeButtonSuccessRegistrationForOneClickModalWindow,"closeButtonSuccessRegistrationForOneClickModalWindow");
        return this;
    }
    public HomePage openMyProfileDropDown(){
        clickOnElement(myProfileDropDown,"myProfileDropDown");
        return this;
    }
    public Header openMyProfileDropDownHeader() {
        getHeader().openMyProfile();
        return getHeader();
    }
    public ResponsibleGamingPage clickResponsibleGaming() {
        clickOnElement(responsibleGamingDropdownOption, "Responsible Gaming option in dropdown");
        return new ResponsibleGamingPage(webDriver);
    }
    public String getWebSiteClientId(){
        checkIsElementVisible(clientIdFromWebSite,"clientIdFromWebSite");
        return  clientIdFromWebSite.getText().replace("\u200E", "").trim();
    }
}
