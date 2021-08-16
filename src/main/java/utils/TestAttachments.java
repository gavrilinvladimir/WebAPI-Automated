package utils;

import com.fasterxml.jackson.databind.ser.Serializers;
import config.ServicesConfig;
import io.qameta.allure.Attachment;
import io.restassured.response.ResponseBody;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;

public class TestAttachments {
    public String requestVar;


    @Attachment(value = "Request sample", type = "json")
    public String getRequestAttachment (ResponseBody request) {
        return requestVar;
    }

    @Attachment(value = "Response sample", type = "json")
    public String getResponseAttachment (BaseResponse rawResponse) {
        String responseAttachment =   "\nStatus code: "+rawResponse.getStatusCode()
                                    + "\nHeader: "+rawResponse.getHeader()
                                    + "\nContent-Type: "+rawResponse.getContentType()
                                    + "\nDate&Time: "+rawResponse.getTime()
                                    + "\nBody: "+rawResponse.getBody();
        return responseAttachment;
    }
}
