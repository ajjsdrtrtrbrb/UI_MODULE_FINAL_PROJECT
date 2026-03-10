package org.pages.BackOffice.boForms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pages.CommonActionsWithElements;

import java.time.LocalDateTime;
import java.util.List;

public class CreateBonusForm extends CommonActionsWithElements {

    @FindBy(xpath = "//form")
    private WebElement bonusForm;

    @FindBy(xpath = "//div[contains(@class,'ant-select-selector')]")
    private WebElement bonusTypeDropdown;

    @FindBy(id = "custom-validation_localizedInfoList_0_name")
    private WebElement bonusNameInput;

    @FindBy(id = "custom-validation_localizedInfoList_0_description")
    private WebElement bonusDescriptionInput;

    @FindBy(id = "custom-validation_visible")
    private WebElement visibleToAllSwitch;

    @FindBy(id = "custom-validation_startTime")
    private WebElement startTimeInput;

    @FindBy(id = "custom-validation_endTime")
    private WebElement endTimeInput;

    @FindBy(id = "custom-validation_daysToWager")
    private WebElement daysToWagerInput;

    @FindBy(xpath = "//button[contains(@class,'ant-btn-icon-only')]")
    private WebElement currencyConfirmBtn;

    @FindBy(xpath = "//button/span[text()='Next Step']")
    private WebElement nextStepBtn;


    @FindBy(id = "custom-validation_wager")
    private WebElement wagerInput;


    @FindBy(id = "custom-validation_financeDataList_0_maxBonusAmount")
    private WebElement fixedBonusAmountInput;

    @FindBy(xpath = "//button/span[text()='Save']")
    private WebElement saveBtn;


    @FindBy(xpath = "//div[contains(@class,'multi-line-cell')]")
    private List<WebElement> bonusList;

    public CreateBonusForm(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public CreateBonusForm checkIsFormOpened() {
        checkIsElementVisible(bonusForm, "Create Bonus Form");
        return this;
    }

    public CreateBonusForm selectBonusType(String type) {
        clickOnElement(bonusTypeDropdown, "Bonus Type Dropdown");
        WebElement option = webDriver.findElement(By.xpath(
                "//div[contains(@class,'ant-select-item-option-content') and text()='" + type + "']"));
        clickOnElement(option, "Bonus Type " + type);
        return this;
    }

    public CreateBonusForm enterBonusName(String name) {
        clearAndEnterTextInToElement(bonusNameInput, name);
        return this;
    }

    public CreateBonusForm enterBonusDescription(String description) {
        clearAndEnterTextInToElement(bonusDescriptionInput, description);
        return this;
    }

    public CreateBonusForm toggleVisibleToAll(boolean visible) {
        boolean isChecked = Boolean.parseBoolean(visibleToAllSwitch.getAttribute("aria-checked"));
        if (isChecked != visible) {
            clickOnElement(visibleToAllSwitch, "Visible To All Switch");
        }
        return this;
    }

    public CreateBonusForm selectStartDate(LocalDateTime dateTime) {
        clickOnElement(startTimeInput, "Start Date Input");
        WebElement dayCell = webDriver.findElement(By.xpath(
                "//td[@title='" + dateTime.toLocalDate() + "']/div[text()='" + dateTime.getDayOfMonth() + "']"));
        clickOnElement(dayCell, "Start Date Day");
        return this;
    }

    public CreateBonusForm selectEndDate(LocalDateTime dateTime) {
        clickOnElement(endTimeInput, "End Date Input");
        WebElement dayCell = webDriver.findElement(By.xpath(
                "//td[@title='" + dateTime.toLocalDate() + "']/div[text()='" + dateTime.getDayOfMonth() + "']"));
        clickOnElement(dayCell, "End Date Day");
        return this;
    }

    public CreateBonusForm enterDaysToWager(int days) {
        clearAndEnterTextInToElement(daysToWagerInput, String.valueOf(days));
        return this;
    }

    public CreateBonusForm selectCurrency(String currency) {
        WebElement checkbox = webDriver.findElement(By.xpath(
                "//span[contains(@class,'ant-transfer-list-content-item-text') and text()='" + currency + "']/preceding-sibling::label//input"));
        clickOnElement(checkbox, "Currency " + currency);
        clickOnElement(currencyConfirmBtn, "Currency Confirm Button");
        return this;
    }

    public CreateBonusForm goToNextStep() {
        clickOnElement(nextStepBtn, "Next Step Button");
        return this;
    }


    public CreateBonusForm enterWager(int wager) {
        clearAndEnterTextInToElement(wagerInput, String.valueOf(wager));
        return this;
    }


    public CreateBonusForm enterFixedBonusAmount(int amount) {
        clearAndEnterTextInToElement(fixedBonusAmountInput, String.valueOf(amount));
        return this;
    }

    public void saveBonus() {
        clickOnElement(saveBtn, "Save Bonus Button");
    }


    public boolean isBonusCreated(String bonusName) {
        for (WebElement bonus : bonusList) {
            if (bonus.getText().trim().equals(bonusName)) {
                return true;
            }
        }
        return false;
    }


    public String getBonusNameInputValue() {
        return bonusNameInput.getAttribute("value");
    }

    public String getBonusDescriptionInputValue() {
        return bonusDescriptionInput.getAttribute("value");
    }


    public void createBonusForTest(String name, String description, String type, String currency, int wager, int amount) {
        checkIsFormOpened()
                .selectBonusType(type)
                .enterBonusName(name)
                .enterBonusDescription(description)
                .toggleVisibleToAll(true)
                .selectStartDate(LocalDateTime.now().minusDays(2))
                .selectEndDate(LocalDateTime.now().plusMonths(1))
                .enterDaysToWager(7)
                .selectCurrency(currency)
                .goToNextStep()
                .enterWager(wager)
                .goToNextStep()
                .enterFixedBonusAmount(amount)
                .saveBonus();
    }
}