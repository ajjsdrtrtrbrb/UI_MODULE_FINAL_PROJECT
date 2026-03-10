package org.pages.commonElementsForAllPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BonusesSection;
import org.pages.CommonActionsWithElements;
import org.pages.LogoutConfirmationModal;
import org.pages.ResponsibleGamingPage;

public class Header extends CommonActionsWithElements {
    @FindBy(xpath = "//span[contains(text(),'Deposit')]")
    WebElement depositButton;
    @FindBy(xpath = "//div[contains(@class,'header__account')]//button[contains(@class,'dropdown-button')]")
    WebElement myProfileButton;
    @FindBy(xpath = "//button//span[text()='Logout']")
    WebElement logOutButton;
    @FindBy(xpath = "//span[@class='account-dropdown__text' and text()='Responsible Gaming']")
    WebElement responsibleGamingLink;
    @FindBy(xpath = "//span[text()='Bonuses']")
    private WebElement bonusesLink;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }

    public Header openMyProfile() {
        clickOnElement(myProfileButton, "My profile button");
        return this;
    }

    public LogoutConfirmationModal clickLogout() {
        clickOnElement(logOutButton, "Logout button");
        return new LogoutConfirmationModal(webDriver);
    }

    public Header clickDeposit() {
        clickOnElement(depositButton, "Deposit button");
        return this;
    }

    public ResponsibleGamingPage openResponsibleGaming() {
        clickOnElement(responsibleGamingLink, "Responsible Gaming link");
        return new ResponsibleGamingPage(webDriver);
    }

    public BonusesSection openBonusesSection() {
        clickOnElement(bonusesLink, "Bonuses link");
        return new BonusesSection(webDriver);
    }


    public Header checkDepositButtonIsVisible() {
        checkIsElementVisible(depositButton, "Deposit button");
        return this;
    }

    public Header checkLogoutIsVisible() {
        checkIsElementVisible(logOutButton, "logoutButton");
        return this;
    }

}
