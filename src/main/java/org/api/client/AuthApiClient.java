package org.api.client;

import io.restassured.response.Response;
import org.api.dto.LoginRequest;
import org.api.dto.LoginResponse;
import org.utils.ConfigProvider;

import static io.restassured.RestAssured.given;

public class AuthApiClient extends BaseApiClient {

    private static final String LOGIN_PATH = "user-management/users:login";

    public AuthApiClient() {
        super();
    }

    public String loginAndGetToken() {
        LoginRequest loginRequest = new LoginRequest(
                ConfigProvider.configHiddenProperties.BO_LOGIN(),
                ConfigProvider.configHiddenProperties.BO_PASSWORD()
        );

        Response response = given(requestSpecification)
                .body(loginRequest)
                .post(LOGIN_PATH)
                .then()
                .statusCode(200)
                .extract()
                .response();

        LoginResponse loginResponse = response.as(LoginResponse.class);
        return loginResponse.getSessionToken();
    }
}