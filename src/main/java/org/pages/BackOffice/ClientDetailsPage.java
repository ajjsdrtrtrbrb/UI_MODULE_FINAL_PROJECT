package org.pages.BackOffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;

public class ClientDetailsPage extends CommonActionsWithElements {
    @FindBy(xpath = "//a[text()='Overview']")
    private WebElement overviewTab;

    @FindBy(xpath = "//span[text()='Temporary SE']/preceding-sibling::span/input")
    private WebElement temporarySECheckboxInput;

    @FindBy(xpath = "//span[text()='Permanently SE']/preceding-sibling::span/input")
    private WebElement permanentlySECheckboxInput;

    public ClientDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ClientDetailsPage openOverviewTab() {
        clickOnElement(overviewTab, "Overview tab");
        return this;
    }

    public ClientDetailsPage checkTemporarySEIsChecked() {
        checkIsElementSelected(temporarySECheckboxInput, "Temporary SE checkbox");
        return this;
    }

    public ClientDetailsPage checkPermanentlySEIsChecked() {
        checkIsElementSelected(permanentlySECheckboxInput, "Permanently SE checkbox");
        return this;
    }
}
