package org.tests.boTests.bonusTests;

import org.baseTest.BaseTest;
import org.data.BonusType;
import org.data.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pages.BackOffice.boForms.CreateBonusForm;

import java.time.LocalDateTime;

public class BonusCreate extends BaseTest {

    @Test
    public void createNoDepositBonus() {
        long currentTime = System.currentTimeMillis();
        String bonusName = "AUTO_BONUS_" + currentTime;
        String bonusDescription = "AUTO_BONUS_DESCRIPION_" + currentTime;

        //  Логин в BO
        pageProvider.getBackOfficeLoginPage()
                .open()
                .login()
                .checkLogOutButtonIsVisible();

        //  Открываем раздел Bonuses через левое меню
        pageProvider.getLeftMenuPage().openBonusesPage();

        // Проверяем, что страница открыта
        Assertions.assertTrue(
                pageProvider.getBonusesPage().isCreateBonusButtonVisible(),
                "Bonuses page wasn't open!"
        );

        //  Создаем бонус
        CreateBonusForm createBonusForm = pageProvider.getBonusesPage()
                .clickOnCreateBonusButton();

        createBonusForm.checkIsFormOpened()
                .selectBonusType(BonusType.NO_DEPOSIT.getValue())  // выбираем тип бонуса
                .enterBonusName(bonusName)
                .enterBonusDescription(bonusDescription)
                .toggleVisibleToAll(true)
                .selectStartDate(LocalDateTime.now().minusDays(2))
                .selectEndDate(LocalDateTime.now().plusMonths(1))
                .enterDaysToWager(7)
                .selectCurrency(TestData.CURRENCY_EUR)
                .goToNextStep()
                .enterWager(1)
                .goToNextStep()
                .enterFixedBonusAmount(10)
                .saveBonus();

        //  Проверка, что бонус появился на странице Bonuses
        Assertions.assertTrue(pageProvider.getBonusesPage().isBonusCreated(bonusName),
                "bonus not found!");
    }




}
