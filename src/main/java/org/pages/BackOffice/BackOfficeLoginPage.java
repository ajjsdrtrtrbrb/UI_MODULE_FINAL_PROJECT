package org.pages.BackOffice;

import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.ConfigProvider;

import java.util.List;

public class BackOfficeLoginPage extends BackOfficeParentPage {
    @FindBy(xpath = "//input[@id='form_item_login']")
    WebElement loginInput;
    @FindBy(xpath = "//input[@type='password' and @id='form_item_password']")
    WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'ant-select-selector')]")
    private WebElement projectSelect;

    @FindBy(xpath = "//div[contains(@class,'ant-select-item-option')]")
    private WebElement firstProjectOption;
    @FindBy(xpath = "//div[contains(@class,'ant-select-item-option')]")
    private List<WebElement> projectOptions;

    public BackOfficeLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public BackOfficeLoginPage open() {
        webDriver.get(ConfigProvider.configProperties.backoffice_url());
        logger.info("back office was opened with url " + ConfigProvider.configProperties.backoffice_url());
        return this;
    }


    public ClientsPage login() {

        clearAndEnterTextInToElement(loginInput, ConfigProvider.configHiddenProperties.BO_LOGIN());
        clearAndEnterTextInToElement(passwordInput, ConfigProvider.configHiddenProperties.BO_PASSWORD());
        clickOnElement(submitButton);


        webDriverWait10.until(ExpectedConditions.visibilityOf(projectSelect));


        projectSelect.click();


        webDriverWait10.until(ExpectedConditions.visibilityOf(firstProjectOption));


        firstProjectOption.click();


        webDriverWait10.until(ExpectedConditions.urlContains("/clients"));

        return new ClientsPage(webDriver);
    }

    public ClientsPage loginAndRedirectToProject() {
        int projectId = 3; // "3 jade" → ID 3


        clearAndEnterTextInToElement(loginInput, ConfigProvider.configHiddenProperties.BO_LOGIN());
        clearAndEnterTextInToElement(passwordInput, ConfigProvider.configHiddenProperties.BO_PASSWORD());
        clickOnElement(submitButton);


        webDriverWait10.until(ExpectedConditions.visibilityOf(projectSelect));


        String projectUrl = ConfigProvider.configProperties.backoffice_url() + "/clients?p=" + projectId;
        webDriver.get(projectUrl);
        logger.info("Redirected to project '" + TestData.BO_PROJECT + "' URL: " + projectUrl);


        webDriverWait10.until(ExpectedConditions.urlContains("/clients?p=" + projectId));


        if (!webDriver.getCurrentUrl().contains("p=" + projectId)) {
            throw new RuntimeException("Не удалось перейти на проект '" + TestData.BO_PROJECT + "'");
        }

        return new ClientsPage(webDriver);
    }


}
