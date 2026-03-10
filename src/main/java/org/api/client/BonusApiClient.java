package org.api.client;

import io.restassured.response.Response;
import org.api.dto.BonusRequest;

import static io.restassured.RestAssured.given;

public class BonusApiClient extends BaseApiClient {

    private static final String BASE_PATH_CREATE = "bonus-service/bonus:create";
    private static final String BASE_PATH_UPDATE = "bonus-service/bonus:update";
    private static final String BASE_PATH_DELETE = "bonus-service/bonus:delete";
    private static final String BASE_PATH_GET = "bonus-service/bonus/";

    public BonusApiClient() {
        super();
    }

    public Response createBonus(BonusRequest bonusRequest) {
        return given(requestSpecification)
                .body(bonusRequest)
                .post(BASE_PATH_CREATE);
    }

    public Response getBonus(Long bonusId) {
        return given(requestSpecification)
                .get(BASE_PATH_GET + bonusId);
    }

    public Response updateBonus(Long bonusId, BonusRequest bonusRequest) {
        bonusRequest.getBonus().setId(bonusId);
        return given(requestSpecification)
                .body(bonusRequest)
                .post(BASE_PATH_UPDATE);
    }

    public Response deleteBonus(Long bonusId) {
        return given(requestSpecification)
                .body("{\"id\":" + bonusId + "}")
                .post(BASE_PATH_DELETE);
    }
}