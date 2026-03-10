package org.pages.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ParentPage;

public class RegistrationForm extends ParentPage {

    @FindBy(xpath = "//div[contains(@class, 'tab-list-item')]//span[text()='Email']")
    private WebElement emailRegistrationTab;
    @FindBy(xpath = "//span[text()='By phone']")
    private WebElement byPhoneRegistrationTab;
    @FindBy(xpath = "//span[text()='One Click']")
    private WebElement oneClickRegistrationTab;


    @FindBy(xpath = "//input[@id='EMAIL']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='LOGIN']")
    private WebElement sighInInputField;
    @FindBy(xpath = "//span[contains(text(),'Registration failed')]")
    private WebElement errorRegistrationMessage;


    @FindBy(xpath = "//input[@name='PHONE']")
    private WebElement phoneInput;


    @FindBy(xpath = "//input[@id='PASSWORD']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='PASSWORD_CONFIRMATION']")
    private WebElement passwordConfirmationInput;
    @FindBy(xpath = "//span[text()='Country']")
    private WebElement countryDropDown;
    @FindBy(xpath = "//div[contains(@class,'select-container')]//label[.//span[text()='Country']]/following-sibling::select")
    private WebElement countrySelect;
    @FindBy(xpath = "//span[text()='Currency List']")
    private WebElement currencyDropDown;
    @FindBy(xpath = "//div[contains(@class,'select-container')]//span[text()='Currency List']/following::select[1]")
    private WebElement selectCurrency;
    @FindBy(xpath = "//span[text()='Day']")
    private WebElement dayDropDown;
    @FindBy(xpath = "//div[contains(@class,'day-picker')]//select")
    private WebElement daySelect;
    @FindBy(xpath = "//span[text()='Month']")
    private WebElement monthDropDown;
    @FindBy(xpath = "//div[contains(@class,'month-picker')]//select")
    private WebElement monthSelect;
    @FindBy(xpath = "//span[text()='Year']")
    private WebElement yearDropDown;
    @FindBy(xpath = "//div[contains(@class,'year-picker')]//select")
    private WebElement yearSelect;
    @FindBy(xpath = "//div[@class='checkbox']")
    private WebElement termsAndConditionsPolicyCheckBox;
    @FindBy(xpath = "//button[@id='signUp']")
    private WebElement signUpButton;

    public RegistrationForm(WebDriver webDriver) {
        super(webDriver);
    }

    public RegistrationForm openEmailTab() {
        clickOnElement(emailRegistrationTab, "Email registration tab");
        return this;
    }

    public RegistrationForm openByPhoneTab() {
        clickOnElement(byPhoneRegistrationTab, "Phone registration tab");
        return this;
    }

    public RegistrationForm openOneClickTab() {
        clickOnElement(oneClickRegistrationTab, "One click registration tab");
        return this;
    }



    public RegistrationForm enterEmail(String email) {
        clearAndEnterTextInToElement(emailInput, email);
        return this;
    }

    public RegistrationForm enterPassword(String password) {
        clearAndEnterTextInToElement(passwordInput, password);
        return this;
    }

    public RegistrationForm enterConfirmPassword(String password) {
        clearAndEnterTextInToElement(passwordConfirmationInput, password);
        return this;
    }

    public RegistrationForm enterSignIn(String signIn) {
        clearAndEnterTextInToElement(sighInInputField, signIn);
        return this;
    }



    public RegistrationForm enterPhone(String phone) {
        clearAndEnterTextInToElement(phoneInput, phone);
        return this;
    }


    public RegistrationForm selectDay(String day) {
        selectValueInElement(daySelect, day);
        return this;
    }

    public RegistrationForm selectMonth(String month) {
        selectValueInElement(monthSelect, month);
        return this;
    }

    public RegistrationForm selectYear(String year) {
        selectValueInElement(yearSelect, year);
        return this;
    }

    public RegistrationForm selectCountry(String country) {
        selectValueInElement(countrySelect, country);
        return this;
    }

    public RegistrationForm selectCurrency(String currency) {
        selectValueInElement(selectCurrency, currency);
        return this;
    }

    public RegistrationForm acceptTerms() {
        setCheckCheckBox(termsAndConditionsPolicyCheckBox);
        return this;
    }

    public RegistrationForm notAcceptTerms() {
        setUnCheckCheckBox(termsAndConditionsPolicyCheckBox);
        return this;
    }

    public RegistrationForm checkRegistrationErrorMessageIsVisible() {
        checkIsElementVisible(errorRegistrationMessage, "errorRegistrationMessage");
        return this;
    }

    public RegistrationForm clickSignUp() {
        clickOnElement(signUpButton, "Sign Up button");
        return this;
    }




    public RegistrationForm registerByEmail(String email, String password,
                                            String day, String month, String year,
                                            String country, String currency) {
        return openEmailTab()
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .selectDay(day)
                .selectMonth(month)
                .selectYear(year)
                .selectCountry(country)
                .selectCurrency(currency)
                .acceptTerms()
                .clickSignUp();
    }

    public RegistrationForm registerByPhone(String phone, String password,
                                            String day, String month, String year,
                                            String country, String currency) {
        return openByPhoneTab()
                .enterPhone(phone)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .selectDay(day)
                .selectMonth(month)
                .selectYear(year)
                .selectCountry(country)
                .selectCurrency(currency)
                .acceptTerms()
                .clickSignUp();
    }

    public RegistrationForm registerByOneClick(String country, String currency) {
        return openOneClickTab()
                .selectCountry(country)
                .selectCurrency(currency)
                .acceptTerms()
                .clickSignUp();
    }
}
