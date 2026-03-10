package org.tests.boTests.bonusTests;
import org.baseTest.BaseTest;
import org.data.BonusType;
import org.data.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.pages.BackOffice.BonusesPage;
import org.pages.BackOffice.ClientsPage;
import org.pages.BackOffice.LeftMenuPage;
import org.pages.BackOffice.boForms.CreateBonusForm;

import java.time.LocalDateTime;

import static org.bouncycastle.cms.RecipientId.password;

public class BonusEdit extends BaseTest{

    @Test
    public void editBonusTest() {
        // Генерируем уникальные имена
        String bonusNameOriginal = "AUTO_BONUS_" + System.currentTimeMillis();
        String bonusNameEdited = bonusNameOriginal + "_EDIT";
        String bonusDescriptionEdited = "Описание после редактирования";

        // Логин в BO и открытие страницы Bonuses
        pageProvider.getBackOfficeLoginPage()
                .open()
                .login();

        BonusesPage bonusesPage = pageProvider.getLeftMenuPage().openBonusesPage();

        // Проверка, что кнопка Create Bonus видна
        Assertions.assertTrue(bonusesPage.isCreateBonusButtonVisible(), "Bonuses page wasn't open!");

        // Создаём бонус для редактирования
        CreateBonusForm createBonusForm = bonusesPage.clickOnCreateBonusButton();
        createBonusForm.createBonusForTest(
                bonusNameOriginal,
                "Аqa test bonus",
                BonusType.NO_DEPOSIT.getValue(),
                TestData.CURRENCY_EUR,
                1,
                10
        );

        // Проверяем, что бонус появился в списке
        Assertions.assertTrue(bonusesPage.isBonusCreated(bonusNameOriginal), "Created bonus not found");

        // Нажимаем кнопку Edit для созданного бонуса и получаем форму редактирования
        CreateBonusForm editForm = bonusesPage.clickEditButtonForBonus(bonusNameOriginal);

        // Редактируем имя и описание на Step 1
        editForm.enterBonusName(bonusNameEdited)
                .enterBonusDescription(bonusDescriptionEdited)
                .goToNextStep()
                .goToNextStep()
                .saveBonus();

        // Проверяем, что бонус появился в списке с новым именем
        Assertions.assertTrue(bonusesPage.isBonusCreated(bonusNameEdited), "Edited bonus not found");

        //  Нажимаем Preview и получаем форму для проверки
        CreateBonusForm previewForm = bonusesPage.clickPreviewButtonForBonus(bonusNameEdited);

        // Проверяем значения в форме
        Assertions.assertEquals(bonusNameEdited, previewForm.getBonusNameInputValue(), "Bonus name doesn't match");
        Assertions.assertEquals(bonusDescriptionEdited, previewForm.getBonusDescriptionInputValue(), "Bonus description doesn't match");
    }
}
