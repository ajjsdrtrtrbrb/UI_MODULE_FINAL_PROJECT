package org.tests.SelfExclusion;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.BackOffice.ClientDetailsPage;
import org.pages.BackOffice.ClientsPage;
import org.pages.ResponsibleGamingPage;
import org.data.TestData;


public class SelfExclusionTest extends BaseTest {
    @Test
    public void selfExclusionOneDayTest() {
        //  Регистрация клиента
        String email = TestData.generateEmail();

        pageProvider.getAuthPage()
                .openAuthPage()
                .openRegistrationForm()
                .openEmailTab()
                .enterEmail(email)
                .enterPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterConfirmPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterSignIn(TestData.generateSignInField())
                .selectDay(TestData.DAY)
                .selectMonth(TestData.MONTH)
                .selectYear(TestData.YEAR)
                .selectCountry(TestData.COUNTRY_GB)
                .selectCurrency(TestData.CURRENCY_EUR)
                .acceptTerms()
                .clickSignUp();

        //  Получение clientId с сайта
        String clientId = pageProvider.getHomePage().getWebSiteClientId();
        logger.info("Client ID: " + clientId);

        //  SelfExclusion на 1 день
        ResponsibleGamingPage rgPage = pageProvider.getHomePage()
                .openMyProfileDropDown()
                .clickResponsibleGaming();

        rgPage.checkSelfExclusionTabIsVisible()
                .selectDuration1Day()
                .checkSubmitButtonIsVisible()
                .submitSelfExclusion();

        //  Проверка, что клиент разлогинен
        pageProvider.getAuthPage()
                .checkSignUpButtonIsVisible();

        //  Проверка в BackOffice
        ClientsPage clientsPage = pageProvider.getBackOfficeLoginPage()
                .open()
                .login()
                .checkLogOutButtonIsVisible();

        clientsPage.selectProjectByName(TestData.BO_PROJECT)
                .searchClientById(clientId);

        ClientDetailsPage clientDetails = clientsPage.openClientProfileById(clientId);

        clientDetails.openOverviewTab()
                .checkTemporarySEIsChecked();
    }

    @Test
    public void selfExclusionForeverTest() {
        // Регистрация клиента
        String email = TestData.generateEmail();

        pageProvider.getAuthPage()
                .openAuthPage()
                .openRegistrationForm()
                .openEmailTab()
                .enterEmail(email)
                .enterPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterConfirmPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterSignIn(TestData.generateSignInField())
                .selectDay(TestData.DAY)
                .selectMonth(TestData.MONTH)
                .selectYear(TestData.YEAR)
                .selectCountry(TestData.COUNTRY_GB)
                .selectCurrency(TestData.CURRENCY_EUR)
                .acceptTerms()
                .clickSignUp();

        // Получение clientId с сайта
        String clientId = pageProvider.getHomePage().getWebSiteClientId();
        logger.info("Client ID: " + clientId);

        //  SelfExclusion Forever
        ResponsibleGamingPage rgPage = pageProvider.getHomePage()
                .openMyProfileDropDown()
                .clickResponsibleGaming();

        rgPage.checkSelfExclusionTabIsVisible()
                .selectDurationForever()
                .checkSubmitButtonIsVisible()
                .submitSelfExclusion();

        //  Проверка, что клиент разлогинен
        pageProvider.getAuthPage()
                .checkSignUpButtonIsVisible();

        //  Проверка в BackOffice
        ClientsPage clientsPage = pageProvider.getBackOfficeLoginPage()
                .open()
                .login()
                .checkLogOutButtonIsVisible();

        clientsPage.selectProjectByName(TestData.BO_PROJECT)
                .searchClientById(clientId);

        ClientDetailsPage clientDetails = clientsPage.openClientProfileById(clientId);

        clientDetails.openOverviewTab()
                .checkPermanentlySEIsChecked();
    }
}
