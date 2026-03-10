package org.tests.boTests.bonusTests;

import org.api.client.AuthApiClient;
import org.api.dto.BonusRequest;
import org.baseTest.BaseTest;
import org.junit.jupiter.api.Test;
import org.api.client.BonusApiClient;
import org.api.dto.BonusRequest.FinanceData;
import io.restassured.response.Response;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.pages.BonusesSection;;
import org.data.TestData;


public class BonusCreateActivateCancel extends BaseTest {
    private final BonusApiClient bonusApiClient = new BonusApiClient();
    private final AuthApiClient authApiClient = new AuthApiClient();
    private final String BONUS_NAME = "aqa_bonus_web_site_activate_cancel";

    @Test
    public void createActivateCancelBonusTest() {
        //Логин в BO и создание бонуса через API
        String token = authApiClient.loginAndGetToken();
        bonusApiClient.setToken(token);

        BonusRequest bonusRequest = new BonusRequest();
        BonusRequest.Bonus bonus = new BonusRequest.Bonus();
        bonus.setProductType("SPORTSBOOK");
        bonus.setVisible(true);
        bonus.setStartTime(System.currentTimeMillis());
        bonus.setEndTime(System.currentTimeMillis() + 7_000_000_000L);
        bonus.setWager(1);
        bonus.setBonusType("NO_DEPOSIT");
        bonus.setActivationMethod("MANUAL");
        bonus.setDebitMethod("MAIN_BALANCE");
        bonus.setWagerType("BONUS");
        bonus.setDefaultLanguage("ENG");
        bonus.setTimeToWager(7_776_000_000L);

        BonusRequest.LocalizedInfo info = new BonusRequest.LocalizedInfo();
        info.setName(BONUS_NAME);
        info.setDescription(BONUS_NAME);
        info.setLanguage("ENG");
        bonus.setLocalizedInfoList(Collections.singletonList(info));

        FinanceData finance = new FinanceData();
        finance.setCurrency("EUR");
        finance.setMaxBonusAmount(10);
        bonus.setFinanceDataList(Collections.singletonList(finance));

        bonusRequest.setBonus(bonus);

        Response createResponse = bonusApiClient.createBonus(bonusRequest);
        assertEquals(200, createResponse.getStatusCode());

        //Логин на сайте
        pageProvider.getAuthPage()
                .openAuthPage()
                .openLoginForm()
                .loginFormIsVisible()
                .enterLogin(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickOnSubmitButton();

        // Переход в My Profile → Bonuses
        BonusesSection bonusesSection = pageProvider.getHomePage()
                .openMyProfileDropDownHeader() // возвращает Header
                .openBonusesSection();
        // Активация бонуса
        bonusesSection.activateBonus(BONUS_NAME)
                .checkBonusStatus(BONUS_NAME, "In progress")
                .checkBonusBalance(10.00);

        //Открытие бонуса и Cancel
        bonusesSection.openBonusDetails(BONUS_NAME)
                .cancelBonus(BONUS_NAME)
                .checkBonusStatus(BONUS_NAME, "Cancelled")
                .checkBonusBalance(0.00);
    }
}
