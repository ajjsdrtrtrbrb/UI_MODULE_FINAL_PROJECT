package org.pages.BackOffice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ClientsPage extends BackOfficeParentPage {

    @FindBy(xpath = "//div[contains(@class,'ant-select-selector')]")
    private WebElement projectDropdown;

    @FindBy(xpath = "//div[contains(@class,'input-container')][.//span[contains(@class,'label-text') and text()='ID']]//input")
    private WebElement clientIdInput;

    @FindBy(xpath = "//button[.//span[text()='Search']]")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'bo-layout-header__logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//span[text()='Profile']")
    private WebElement myProfileButton;

    private LeftMenuPage leftMenu;

    public ClientsPage(WebDriver webDriver) {
        super(webDriver);
        leftMenu = new LeftMenuPage(webDriver);
    }

    public ClientDetailsPage openClientProfileById(String clientId) {
        By clientRowById = By.xpath(
                "//div[contains(@class,'ag-center-cols-container')]" +
                        "/div[contains(@class,'ag-row') and not(contains(@class,'ag-hidden'))]" +
                        "[.//span[contains(@class,'custom-cell__content_text') and contains(text(),'" + clientId + "')]]"
        );

        WebElement clientRow = webDriverWait20.until(
                driver -> driver.findElement(clientRowById)
        );

        WebElement profileButton = clientRow.findElement(By.xpath(".//span[text()='Profile']"));

        clickOnElement(profileButton, "Profile button for clientId " + clientId);

        return new ClientDetailsPage(webDriver);
    }

    public void openBonusesPage() {
        leftMenu.openBonusesPage();
    }

    public ClientsPage waitForPageToLoad() {

        webDriverWait20.until(driver ->
                driver.getCurrentUrl().contains("/clients")
        );

        webDriverWait20.until(
                ExpectedConditions.visibilityOf(logoutButton)
        );

        logger.info("Clients page fully loaded");
        return this;
    }

    public ClientsPage selectProjectByName(String projectName) {
        webDriverWait20.until(ExpectedConditions.elementToBeClickable(projectDropdown)).click();

        By optionXpath = By.xpath(
                "//div[contains(@class,'ant-select-item-option-content') and normalize-space(text())='"
                        + projectName + "']"
        );

        WebElement option = webDriverWait20.until(
                ExpectedConditions.elementToBeClickable(optionXpath)
        );

        ((JavascriptExecutor) webDriver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        ((JavascriptExecutor) webDriver)
                .executeScript("arguments[0].click();", option);

        logger.info("Project '" + projectName + "' selected");
        return this;
    }

    public ClientsPage searchClientById(String clientId) {

        clearAndEnterTextInToElement(clientIdInput, clientId);
        clickOnElement(searchButton);

        logger.info("Clicked Search for clientId: " + clientId);

        waitForGridToLoad();

        return this;
    }

    private void waitForGridToLoad() {
        // Ждём появления контейнера таблицы
        By gridContainer = By.xpath("//div[contains(@class,'ag-center-cols-container')]");
        webDriverWait20.until(ExpectedConditions.visibilityOfElementLocated(gridContainer));

        webDriverWait20.until(driver ->
                driver.findElements(By.xpath(
                        "//div[contains(@class,'ag-row') and not(contains(@class,'ag-hidden'))]"
                )).size() >= 0
        );
    }

    public boolean isClientUniqueAndMatches(String clientId) {
        try {
            String clientIdDigits = clientId.replaceAll("\\D", "");

            By clientRowById = By.xpath(
                    "//div[contains(@class,'ag-center-cols-container')]" +
                            "/div[contains(@class,'ag-row') and not(contains(@class,'ag-hidden'))]" +
                            "[.//span[contains(@class,'custom-cell__content_text') " +
                            "and contains(text(),'" + clientIdDigits + "')]]"
            );

            Boolean exactlyOne = webDriverWait20.until(driver ->
                    driver.findElements(clientRowById).size() == 1
            );

            if (!exactlyOne) {
                logger.warn("Client ID not unique or not found: " + clientIdDigits);
                return false;
            }

            logger.info("Client ID found and unique: " + clientIdDigits);
            return true;

        } catch (Exception e) {
            logger.error("Error while checking client ID: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean searchClientByIdAndCheckUnique(String projectName, String clientId) {
        waitForPageToLoad();
        selectProjectByName(projectName);
        searchClientById(clientId);
        return isClientUniqueAndMatches(clientId);
    }

    public ClientsPage checkLogOutButtonIsVisible() {
        webDriverWait20.until(ExpectedConditions.visibilityOf(logoutButton));
        logger.info("Logout button is visible");
        return this;
    }
}