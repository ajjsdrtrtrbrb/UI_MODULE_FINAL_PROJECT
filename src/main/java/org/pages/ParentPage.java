package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPage extends CommonActionsWithElements{
   // protected String baseUrl="https://asiabets247.preprod.k8s-hz.atlas-iac.com/";
    @FindBy(xpath = "//span[text()='YES']")
    private WebElement ageConfirmYesButton;
    @FindBy(xpath = "//div[@class='popup__content']")
    private WebElement welcomePopupContent;

    @FindBy(
            xpath = "//div[@class='popup__content']/following-sibling::div[@class='popup__footer']//span[text()='Sign Up']"
    )
    private WebElement signUpButtonInWelcomePopup;

    @FindBy(
            xpath = "//div[@class='popup__content']/following-sibling::div[@class='popup__footer']//span[text()='SIGN IN']"
    )
    private WebElement signInButtonInWelcomePopup;
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
    protected void confirmAgeIfPresent(){
        try{
            if(ageConfirmYesButton.isDisplayed()){
                clickOnElement(ageConfirmYesButton,"Age confirmation YES");
            }
        }
        catch (Exception ignored){

        }
    }
    protected void openSignUpFromWelcomePopupIfPresent(){
        try {
            if(welcomePopupContent.isDisplayed()){
                clickOnElement(signUpButtonInWelcomePopup,"Sign Up button in welcome popup");
            }
        }
        catch (Exception ignored){

        }
    }
    protected void openSignInFromWelcomePopupIfPresent(){
        try{
            if(welcomePopupContent.isDisplayed()){
                clickOnElement(signInButtonInWelcomePopup,"Sign In button in welcome popup");
            }

        }
        catch (Exception ignored){

        }
    }
}
