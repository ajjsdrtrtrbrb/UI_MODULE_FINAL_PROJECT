package org.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.utils.ConfigProvider;

public class BaseApiClient {

    protected RequestSpecification requestSpecification;

    public BaseApiClient() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.configProperties.bo_api_url())
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public void setToken(String token) {
        requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(requestSpecification)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}