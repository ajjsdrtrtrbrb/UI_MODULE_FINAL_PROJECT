package org.api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.api.dto.LoginRequest;
import org.api.dto.LoginResponse;
import org.utils.ConfigProvider;

public class LoginApiClient extends BaseApiClient {

    private final String basePath = "user-management/users:login";

    public LoginApiClient() {
        super();
    }

    public String loginAndGetToken() {
        LoginRequest loginRequest = new LoginRequest(
                ConfigProvider.configHiddenProperties.BO_LOGIN(),
                ConfigProvider.configHiddenProperties.BO_PASSWORD()
        );

        Response response = RestAssured.given(requestSpecification)
                .body(loginRequest)
                .post(basePath)
                .then()
                .statusCode(200)
                .extract()
                .response();


        LoginResponse loginResponse = response.as(LoginResponse.class);


        return loginResponse.getSessionToken();
    }
}