package org.pages.BackOffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;

public class LeftMenuPage extends CommonActionsWithElements {

    @FindBy(xpath = "//div[@data-menu-id='crm']")
    private WebElement crmMenu;

    @FindBy(xpath = "//li[@data-menu-id='crm.bonuses']//a")
    private WebElement bonusesButton;

    public LeftMenuPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openCrmSection() {
        String expanded = crmMenu.getAttribute("aria-expanded");

        if (!expanded.equals("true")) {
            clickOnElement(crmMenu, "CRM menu");
        }
    }

    public BonusesPage openBonusesPage() {
        openCrmSection();
        clickOnElement(bonusesButton, "Bonuses section");
        return new BonusesPage(webDriver);
    }
}