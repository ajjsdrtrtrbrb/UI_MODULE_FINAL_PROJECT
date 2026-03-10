package org.tests.RegistrationLoginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoLogin extends BaseTest {

    @Test
    public void validLoginInBo(){
        pageProvider.getBackOfficeLoginPage()
                .open()
                .login()
                .checkLogOutButtonIsVisible();
    }
    @Test
    public void validLoginOn3Project(){
        pageProvider.getBackOfficeLoginPage()
                .open()
                .loginAndRedirectToProject();
    }

}
