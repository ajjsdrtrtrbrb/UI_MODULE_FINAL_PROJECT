package org.apiTests.bonusTests;

import org.api.client.AuthApiClient;
import org.api.client.BonusApiClient;
import org.api.dto.BonusRequest;
import org.api.dto.BonusRequest.Bonus;
import org.api.dto.BonusRequest.FinanceData;
import org.api.dto.BonusRequest.LocalizedInfo;
import org.api.dto.BonusResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BonusCreateEditDeleteGet {

    private final BonusApiClient bonusApiClient = new BonusApiClient();
    private final AuthApiClient authApiClient = new AuthApiClient();

    @Test
    public void crudBonusTest() {
        // --- LOGIN и получение токена ---
        String token = authApiClient.loginAndGetToken();

        // Подставляем токен в BonusApiClient
        bonusApiClient.setToken(token);

        // --- CREATE ---
        BonusRequest bonusRequest = new BonusRequest();
        Bonus bonus = new Bonus();
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

        LocalizedInfo info = new LocalizedInfo();
        info.setName("aqa_bonus");
        info.setDescription("aqa_bonus");
        info.setLanguage("ENG");
        bonus.setLocalizedInfoList(Collections.singletonList(info));

        FinanceData finance = new FinanceData();
        finance.setCurrency("EUR");
        finance.setMaxBonusAmount(1000);
        bonus.setFinanceDataList(Collections.singletonList(finance));

        bonusRequest.setBonus(bonus);

        Response createResponse = bonusApiClient.createBonus(bonusRequest);
        assertEquals(200, createResponse.getStatusCode());

        Long bonusId = createResponse.jsonPath().getLong("bonus.id");
        assertNotNull(bonusId, "Bonus must be created");

        // --- READ ---
        Response getResponse = bonusApiClient.getBonus(bonusId);
        assertEquals(200, getResponse.getStatusCode());

        BonusResponse bonusResponse = getResponse.as(BonusResponse.class);
        assertEquals("NO_DEPOSIT", bonusResponse.getBonusType());
        assertNotNull(bonusResponse.getLocalizedInfoList());
        assertEquals("aqa_bonus", bonusResponse.getLocalizedInfoList().get(0).getName());

        // --- UPDATE ---
        bonus.getLocalizedInfoList().get(0).setName("aqa_bonus_updated");
        bonus.setVisible(false);
        bonusRequest.setBonus(bonus);

        Response updateResponse = bonusApiClient.updateBonus(bonusId, bonusRequest);
        assertEquals(200, updateResponse.getStatusCode());

        Response getAfterUpdate = bonusApiClient.getBonus(bonusId);
        BonusResponse updated = getAfterUpdate.as(BonusResponse.class);
        assertEquals("aqa_bonus_updated", updated.getLocalizedInfoList().get(0).getName());

        // --- DELETE ---
        Response deleteResponse = bonusApiClient.deleteBonus(bonusId);
        assertEquals(200, deleteResponse.getStatusCode());
    }
}