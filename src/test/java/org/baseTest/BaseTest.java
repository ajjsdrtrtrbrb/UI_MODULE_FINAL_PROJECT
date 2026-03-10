package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.utils.ConfigProvider;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PageProvider;

import java.time.Duration;

public class BaseTest {

    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_IMPLICIT_WAIT()));

        pageProvider = new PageProvider(webDriver);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
            logger.info("Browser was closed");
        }
    }
}