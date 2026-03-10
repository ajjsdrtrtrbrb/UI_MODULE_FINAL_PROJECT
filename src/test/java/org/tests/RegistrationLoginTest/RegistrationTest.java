package org.tests.RegistrationLoginTest;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.pages.BackOffice.ClientsPage;
import org.utils.ConfigProvider;


import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    private String email = TestData.generateEmail();
    private String newLogin = TestData.generateSignInField();
    private String phone = TestData.generatePhone();

    @Test
    public void registrationByEmail() throws InterruptedException {
        //  Открытие страницы регистрации
        pageProvider.getAuthPage()
                .openAuthPage()
                .openRegistrationForm();

        //  Регистрация пользователя через Email
        pageProvider.getRegistrationForm()
                .openEmailTab()
                .enterEmail(email)
                .enterPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterConfirmPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterSignIn(newLogin)
                .selectDay(TestData.DAY)
                .selectMonth(TestData.MONTH)
                .selectYear(TestData.YEAR)
                .selectCountry(TestData.COUNTRY_GB)
                .selectCurrency(TestData.CURRENCY_EUR)
                .acceptTerms()
                .clickSignUp();

        //  Проверка успешной регистрации на сайте
        pageProvider.getHomePage()
                .openMyProfileDropDown()
                .getHeader()
                .checkLogoutIsVisible()
                .checkDepositButtonIsVisible();

        //  Получение clientId с сайта
        String clientId = pageProvider.getHomePage()
                .getWebSiteClientId();
        logger.info("Client ID from website: " + clientId);

        //  Логин в BackOffice
        ClientsPage clientsPage = pageProvider.getBackOfficeLoginPage()
                .open()
                .login()
                .checkLogOutButtonIsVisible(); // ждём появления кнопки logout

        //  Выбор проекта и поиск клиента
        boolean clientExistsAndUnique = clientsPage
                .searchClientByIdAndCheckUnique(TestData.BO_PROJECT, clientId);

        //  Проверка, что клиент найден и уникален
        Assert.assertTrue("Client should exist in BackOffice and be unique", clientExistsAndUnique);


    }


    @Test
    public void registrationByPhone() {
        pageProvider.getAuthPage()
                .openAuthPage()
                .openRegistrationForm()
                .openByPhoneTab()
                .enterPhone(phone)
                .selectCurrency(TestData.CURRENCY_USD)
                .acceptTerms()
                .selectCountry(TestData.COUNTRY_US)
                .selectCurrency(TestData.CURRENCY_USD)
                .enterPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterConfirmPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .selectDay(TestData.DAY)
                .selectMonth(TestData.MONTH)
                .selectYear(TestData.YEAR)
                .clickSignUp();
        pageProvider.getHomePage()
                .openMyProfileDropDown()
                .getHeader()
                .checkLogoutIsVisible()
                .checkDepositButtonIsVisible();
    }

    @Test
    public void registrationByOneClick() {
        pageProvider.getAuthPage()
                .openAuthPage()
                .openRegistrationForm()
                .openOneClickTab()
                .selectCurrency(TestData.CURRENCY_USD)
                .acceptTerms()
                .selectCountry(TestData.COUNTRY_GB)
                .clickSignUp();
        pageProvider.getHomePage()
                .checkIsSuccessRegistrationForOneClickModalWindowVisible()
                .closeSuccessRegistrationForOneClickModalWindow()
                .openMyProfileDropDown()
                .getHeader()
                .checkLogoutIsVisible()
                .checkDepositButtonIsVisible()
                .checkDepositButtonIsVisible();
    }

    @Test
    public void registrationByEmailWithAcceptTermsValidationCheck() {
        pageProvider.getAuthPage()
                .openAuthPage()
                .openRegistrationForm()
                .openEmailTab()
                .enterEmail(email)
                .enterPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .enterConfirmPassword(TestData.VALID_PASSWORD_FOR_REGISTRATION)
                .selectCountry(TestData.COUNTRY_GB)
                .selectCurrency(TestData.CURRENCY_EUR)
                .enterSignIn(newLogin)
                .selectDay(TestData.DAY)
                .selectMonth(TestData.MONTH)
                .selectYear(TestData.YEAR)
                .notAcceptTerms()
                .clickSignUp()
                .checkRegistrationErrorMessageIsVisible()
                .acceptTerms()
                .clickSignUp();
        pageProvider.getHomePage()
                .openMyProfileDropDown()
                .getHeader()
                .checkLogoutIsVisible()
                .checkDepositButtonIsVisible();

    }
}
