package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.commonElementsForAllPages.Header;

public class ResponsibleGamingPage extends ParentPage{
    @FindBy(xpath = "//span[text()='Self Exclusion']")
    private WebElement selfExclusionTab;
    @FindBy(xpath = "//span[text()='1 day']")
    private WebElement duration1Day;
    @FindBy(xpath = "//span[text()='Forever']")
    private WebElement durationForever;
    @FindBy(xpath = "//span[text()='Submit Self exclusion']")
    private WebElement submitSelfExclusionButton;

    public ResponsibleGamingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Header getHeader() {
        return new Header(webDriver);
    }
    public ResponsibleGamingPage openSelfExclusionTab() {
        clickOnElement(selfExclusionTab, "Self Exclusion tab");
        return this;
    }

    public ResponsibleGamingPage selectDuration1Day(){
        clickOnElement(duration1Day,"Duration 1 day");
        return this;
    }

    public ResponsibleGamingPage selectDurationForever(){
        clickOnElement(durationForever,"Duration Forever");
        return this;
    }

    public AuthPage submitSelfExclusion() {
        clickOnElement(submitSelfExclusionButton, "Submit Self Exclusion button");
        return new AuthPage(webDriver);
    }

    public ResponsibleGamingPage checkSelfExclusionTabIsVisible() {
        checkIsElementVisible(selfExclusionTab, "Self Exclusion tab");
        return this;
    }

    public ResponsibleGamingPage checkSubmitButtonIsVisible() {
        checkIsElementVisible(submitSelfExclusionButton, "Submit Self Exclusion button");
        return this;
    }
}
