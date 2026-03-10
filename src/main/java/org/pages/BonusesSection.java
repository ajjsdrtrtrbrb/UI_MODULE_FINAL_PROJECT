package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BonusesSection extends CommonActionsWithElements {

    public BonusesSection(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    @FindBy(xpath = "//div[contains(@class,'profile-bonus-item')]")
    private List<WebElement> allBonuses;

    @FindBy(xpath = "//div[contains(@class,'balance__bonus')]//span[contains(@class,'balance__amount')]")
    private WebElement bonusBalance;


    private WebElement getBonusByName(String bonusName) {
        for (WebElement bonus : allBonuses) {
            WebElement nameElement = bonus.findElement(
                    By.xpath(".//div[contains(@class,'profile-bonus-item__col') and contains(@class,'name')]"));
            if (nameElement.getText().trim().equals(bonusName)) {
                return bonus;
            }
        }
        throw new RuntimeException("Bonus with name '" + bonusName + "' not found!");
    }

    private WebElement getActivateButton(WebElement bonus) {
        return bonus.findElement(
                By.xpath(".//div[contains(@class,'profile-bonus-item__col') and contains(@class,'status')]//button/span[text()='Activate']"));
    }

    private WebElement getCancelButton(WebElement bonus) {
        return bonus.findElement(
                By.xpath(".//div[contains(@class,'profile-bonus-item__col') and contains(@class,'status')]//button/span[text()='Cancel']"));
    }

    private WebElement getStatusElement(WebElement bonus) {
        List<WebElement> inProgress = bonus.findElements(
                By.xpath(".//div[contains(@class,'profile-bonus-activated')]//span[contains(@class,'profile-bonus-activated__text')]"));
        if (!inProgress.isEmpty()) return inProgress.get(0);

        List<WebElement> cancelled = bonus.findElements(
                By.xpath(".//div[contains(@class,'profile-bonus-item__col') and contains(@class,'status')]//span[contains(@class,'profile-bonus__status')]"));
        if (!cancelled.isEmpty()) return cancelled.get(0);

        return null;
    }


    public BonusesSection activateBonus(String bonusName) {
        WebElement bonus = getBonusByName(bonusName);
        clickOnElement(getActivateButton(bonus), "Activate button for bonus '" + bonusName + "'");
        return this;
    }

    public BonusesSection openBonusDetails(String bonusName) {
        WebElement bonus = getBonusByName(bonusName);
        WebElement nameElement = bonus.findElement(
                By.xpath(".//div[contains(@class,'profile-bonus-item__col') and contains(@class,'name')]"));
        clickOnElement(nameElement, "Open bonus details for '" + bonusName + "'");
        return this;
    }

    public BonusesSection cancelBonus(String bonusName) {
        WebElement bonus = getBonusByName(bonusName);
        clickOnElement(getCancelButton(bonus), "Cancel button for bonus '" + bonusName + "'");
        return this;
    }


    public BonusesSection checkBonusStatus(String bonusName, String expectedStatus) {
        WebElement bonus = getBonusByName(bonusName);
        WebElement statusElement = getStatusElement(bonus);
        if (statusElement == null) throw new AssertionError("Status element not found for bonus: " + bonusName);

        String actualStatus = statusElement.getText().trim();
        if (expectedStatus.equalsIgnoreCase("In progress")) {
            if (!actualStatus.startsWith("In progress")) {
                throw new AssertionError("Expected 'In progress' but was: " + actualStatus);
            }
        } else if (expectedStatus.equalsIgnoreCase("Cancelled")) {
            if (!actualStatus.equals("Cancelled")) {
                throw new AssertionError("Expected 'Cancelled' but was: " + actualStatus);
            }
        }
        return this;
    }

    public BonusesSection checkBonusBalance(double expectedBalance) {
        String text = bonusBalance.getText().replace("€", "").trim();
        double actualBalance = Double.parseDouble(text);
        if (actualBalance != expectedBalance) {
            throw new AssertionError("Expected bonus balance: " + expectedBalance + " but was: " + actualBalance);
        }
        return this;
    }
}