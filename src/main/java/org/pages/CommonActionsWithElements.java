package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15,webDriverWait20;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        webDriverWait20=new WebDriverWait(webDriver,Duration.ofSeconds(20));
    }

    protected void clearAndEnterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void checkIsElementEnable(WebElement webElement) {
        Assert.assertTrue("Element " + getElementName(webElement) + " is not enabled", isElementEnable(webElement));
        logger.info("Element " + getElementName(webElement) + " is enabled");
    }

    private boolean isElementEnable(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            boolean state = webElement.isEnabled();
            logger.info("Element " + getElementName(webElement) + " state " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found or not enabled");
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertTrue("Element " + getElementName(webElement) + " is not visible", isElementVisible(webElement));
        logger.info("Element " + getElementName(webElement) + " is visible");
    }
    protected void checkIsElementVisible(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            Assert.assertTrue("Element " + elementName + " is not visible", webElement.isDisplayed());
            logger.info("Element " + elementName + " is visible");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    private boolean isElementVisible(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            boolean state = webElement.isDisplayed();
            logger.info("Element " + getElementName(webElement) + " state " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not visible");
            return false;
        }
    }

    protected void selectTextInElement(WebElement webElement, String text) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectValueInElement(WebElement webElement, String value) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void checkTextInElement(WebElement webElement, String text) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            String actual = webElement.getText();
            Assert.assertEquals("Text does not match expected value", text, actual);
            logger.info("Actual text '" + actual + "' matches expected text '" + text + "' in element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void setCheckCheckBox(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));

            String tagName = webElement.getTagName();
            boolean isInputCheckbox = "input".equalsIgnoreCase(tagName) &&
                    "checkbox".equalsIgnoreCase(webElement.getAttribute("type"));

            if (isInputCheckbox) {
                if (!webElement.isSelected()) {
                    webElement.click();
                    logger.info(getElementName(webElement) + " was selected (normal checkbox)");
                } else {
                    logger.info(getElementName(webElement) + " already selected (normal checkbox)");
                }
            } else {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                js.executeScript("arguments[0].click();", webElement);
                logger.info(getElementName(webElement) + " was selected (custom checkbox via JS)");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void setUnCheckCheckBox(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            String tagName = webElement.getTagName();
            boolean isCheckBox = "input".equalsIgnoreCase(tagName) &&
                    "checkbox".equalsIgnoreCase(webElement.getAttribute("type"));
            if (isCheckBox) {
                if (webElement.isSelected()) {
                    webElement.click();
                    logger.info(getElementName(webElement) + " unselected");
                } else {
                    logger.info(getElementName(webElement) + " was already unselected");
                }

            } else {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                Boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", webElement);
                if (isChecked != null && isChecked) {
                    js.executeScript("arguments[0].click();", webElement);
                    logger.info(getElementName(webElement) + " unselected (via JS)");
                } else {
                    logger.info(getElementName(webElement) + " was already unselected (via JS)");
                }
            }

        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void setStatusForCheckBox(WebElement webElement, String status) {
        if (status.equalsIgnoreCase("check")) {
            setCheckCheckBox(webElement);
        } else if (status.equalsIgnoreCase("uncheck")) {
            setUnCheckCheckBox(webElement);
        } else {
            logger.info("Invalid status for element " + getElementName(webElement));
            printErrorAndStopTest();
        }
    }

    protected String getElementName(WebElement webElement) {
        try {
            String name = webElement.getAccessibleName();
            if (name != null && !name.isBlank()) return name;

            name = webElement.getAttribute("id");
            if (name != null && !name.isBlank()) return name;

            name = webElement.getAttribute("name");
            if (name != null && !name.isBlank()) return name;

            return "unknown element";
        } catch (Exception e) {
            return "unknown element";
        }
    }
    public void checkIsElementSelected(WebElement element, String elementName) {
        try {
            if (!element.isSelected()) {
                throw new AssertionError(elementName + " is NOT selected!");
            } else {
                logger.info(elementName + " is selected");
            }
        } catch (Exception e) {
            logger.error("Error checking if element is selected: " + elementName, e);
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends CommonActionsWithElements> T waitUntilVisible(WebElement webElement) {
        webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
        return (T) this;
    }

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element");
    }
}
