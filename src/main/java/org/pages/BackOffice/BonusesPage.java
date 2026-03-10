package org.pages.BackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BackOffice.boForms.CreateBonusForm;
import org.pages.CommonActionsWithElements;

import java.util.List;

public class BonusesPage extends CommonActionsWithElements {


    @FindBy(xpath = "//button[.//span[text()='Create Bonus']]")
    private WebElement createBonusButton;

    @FindBy(xpath = "//div[contains(@class,'multi-line-cell')]")
    private List<WebElement> bonusList;


    @FindBy(xpath = "//button[.//span[text()='Show Filters']]")
    private WebElement showFiltersButton;

    @FindBy(xpath = "//button[.//span[text()='Clean Filters']]")
    private WebElement cleanFiltersButton;

    @FindBy(xpath = "//div[contains(@class,'ant-select-selection-overflow')]//input[@role='combobox']")
    private WebElement statusesSelectInput;

    @FindBy(xpath = "//div[contains(@class,'ant-select-item-option-content') and text()='ARCHIVED']")
    private WebElement archivedOption;

    @FindBy(xpath = "//button[.//span[text()='Search']]")
    private WebElement searchButton;


    public BonusesPage(WebDriver webDriver) {
        super(webDriver);
    }


    public CreateBonusForm clickOnCreateBonusButton() {
        clickOnElement(createBonusButton, "Create Bonus button");
        return new CreateBonusForm(webDriver);
    }

    public boolean isBonusCreated(String bonusName) {
        try {
            return webDriverWait15.until(driver ->
                    bonusList.stream()
                            .anyMatch(b -> b.getText().trim().equals(bonusName))
            );
        } catch (Exception e) {
            logger.warn("Bonus not found: " + bonusName);
            return false;
        }
    }

    public boolean isCreateBonusButtonVisible() {
        try {
            webDriverWait10.until(driver -> createBonusButton.isDisplayed());
            return true;
        } catch (Exception e) {
            logger.warn("Create Bonus Button is not visible!");
            return false;
        }
    }

    public CreateBonusForm clickEditButtonForBonus(String bonusName) {
        WebElement editButton = webDriver.findElement(By.xpath(
                "//div[contains(@class,'multi-line-cell') and normalize-space(text())='" + bonusName + "']" +
                        "/following-sibling::div//button[@title='Edit']"
        ));
        clickOnElement(editButton, "Edit button for bonus " + bonusName);
        return new CreateBonusForm(webDriver);
    }

    public CreateBonusForm clickPreviewButtonForBonus(String bonusName) {
        WebElement previewButton = webDriver.findElement(By.xpath(
                "//div[contains(@class,'multi-line-cell') and normalize-space(text())='" + bonusName + "']" +
                        "/following-sibling::div//button[@title='Preview']"
        ));
        clickOnElement(previewButton, "Preview button for bonus " + bonusName);
        return new CreateBonusForm(webDriver);
    }

    public void clickArchiveButtonForBonus(String bonusName) {
        WebElement archiveButton = webDriver.findElement(By.xpath(
                "//div[contains(@class,'multi-line-cell') and normalize-space(text())='" + bonusName + "']" +
                        "/following-sibling::div//button[@title='Archive']"
        ));
        clickOnElement(archiveButton, "Archive button for bonus " + bonusName);
    }

    public void clickArchiveFirstBonus() {
        if (!bonusList.isEmpty()) {
            WebElement firstBonusName = bonusList.get(0);
            String bonusName = firstBonusName.getText().trim();
            clickArchiveButtonForBonus(bonusName);
        }
    }

    public BonusesPage clickShowFilters() {
        clickOnElement(showFiltersButton, "Show Filters button");
        return this;
    }

    public BonusesPage clickCleanFilters() {
        clickOnElement(cleanFiltersButton, "Clean Filters button");
        return this;
    }

    public BonusesPage selectStatusArchived() {
        clickOnElement(statusesSelectInput, "Statuses select input");
        clickOnElement(archivedOption, "Select ARCHIVED status");
        return this;
    }

    public BonusesPage clickSearch() {
        clickOnElement(searchButton, "Search button");
        return this;
    }

    public String getBonusStatus(String bonusName) {
        WebElement statusElement = webDriver.findElement(By.xpath(
                "//div[contains(@class,'multi-line-cell') and normalize-space(text())='" + bonusName + "']" +
                        "/following-sibling::div//span[contains(@class,'bo-tag-status')]"
        ));
        return statusElement.getText().trim();
    }

    public String getFirstBonusName() {
        if (bonusList.isEmpty()) return "";
        return bonusList.get(0).getText().trim();
    }

    public String getFirstBonusStatus() {
        if (bonusList.isEmpty()) return "";
        String firstBonusName = bonusList.get(0).getText().trim();
        return getBonusStatus(firstBonusName);
    }
}