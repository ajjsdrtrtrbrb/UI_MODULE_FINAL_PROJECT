package org.tests.boTests.bonusTests;

import org.baseTest.BaseTest;
import org.data.BonusType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.pages.BackOffice.BonusesPage;
import org.pages.BackOffice.boForms.CreateBonusForm;
import org.data.TestData;

import java.time.LocalDateTime;

public class BonusArchive extends BaseTest {

    @Test
    public void createAndArchiveBonusTest() {
        //  Входим в BO
        pageProvider.getBackOfficeLoginPage()
                .open()
                .login()
                .checkLogOutButtonIsVisible();

        // Создаем новый бонус
        BonusesPage bonusesPage = pageProvider.getLeftMenuPage()
                .openBonusesPage();

        String bonusName = "AUTO_BONUS_" + System.currentTimeMillis();

        CreateBonusForm bonusForm = bonusesPage.clickOnCreateBonusButton();
        bonusForm.checkIsFormOpened()
                .selectBonusType(BonusType.NO_DEPOSIT.getValue())
                .enterBonusName(bonusName)
                .enterBonusDescription("AUTO_BONUS_DESC_" + System.currentTimeMillis())
                .toggleVisibleToAll(true)
                .selectStartDate(LocalDateTime.now().minusDays(2))
                .selectEndDate(LocalDateTime.now().plusMonths(1))
                .enterDaysToWager(7)
                .goToNextStep()
                .enterWager(1)
                .goToNextStep()
                .enterFixedBonusAmount(10)
                .saveBonus();

        // Проверка: бонус появился первым и статус Active
        String firstBonus = bonusesPage.getFirstBonusName();
        String firstBonusStatus = bonusesPage.getFirstBonusStatus();

        Assertions.assertEquals(bonusName, firstBonus, "Created bonus should be first in the list");
        Assertions.assertEquals("Active", firstBonusStatus, "Bonus status should be Active");

        // Архивируем бонус
        bonusesPage.clickArchiveFirstBonus();

        //  Фильтруем по статусу Archived
        bonusesPage.clickShowFilters()
                .clickCleanFilters()       // Сброс предыдущих фильтров
                .selectStatusArchived()    // Выбираем ARCHIVED
                .clickSearch();            // Применяем фильтр

        //Проверка: бонус вверху со статусом Archived
        String archivedBonus = bonusesPage.getFirstBonusName();
        String archivedStatus = bonusesPage.getFirstBonusStatus();

        Assertions.assertEquals(bonusName, archivedBonus, "Archived bonus should be first in the list");
        Assertions.assertEquals("Archived", archivedStatus, "Bonus status should be Archived");
    }
}
