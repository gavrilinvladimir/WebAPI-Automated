package client;

import config.EnvConfig;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.BaseModel;
import org.apache.log4j.Logger;
import response.BaseResponse;


import static io.restassured.RestAssured.*;

public class HttpClient {
    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    public static BaseResponse get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static BaseResponse post(String endpoint, BaseModel body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static BaseResponse put(String endpoint, BaseModel body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static BaseResponse delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static BaseResponse sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    @Step ("Send Request")
    private static BaseResponse sendRequest(Method method, String endpoint, BaseModel body) {
        String url = EnvConfig.HOST + EnvConfig.LIB + endpoint;
        RequestSpecification spec = given().filter(new AllureRestAssured()).header("Content-Type","application/json");
        if (body != null) spec.body(body);
        Response rawResponse = spec.request(method, url);
        LOG.info("Endpoint URL is: "+url);
        return new BaseResponse(rawResponse);
    }
}
