package client;

import config.EnvConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import response.BaseResponse;

import static io.restassured.RestAssured.*;

public class HttpClient {
    private static Logger LOG = Logger.getLogger(HttpClient.class);

    public static BaseResponse get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static BaseResponse post(String endpoint, String body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static BaseResponse put(String endpoint, String body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static BaseResponse delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static BaseResponse sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    private static BaseResponse sendRequest(Method method, String endpoint, String body) {
        String url = EnvConfig.HOST + EnvConfig.LIB + endpoint;
        RequestSpecification spec = given();
        spec = addHeader(spec);
        if (body != null) spec.body(body);
        Response rawResponse = spec.request(method, url);
        return new BaseResponse(rawResponse);
    }

    public static RequestSpecification addHeader (RequestSpecification spec){
        spec = given()
                .header("Content-Type","application/json");
        return spec;
    }
}
