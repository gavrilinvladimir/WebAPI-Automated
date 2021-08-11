package response;

import io.restassured.response.Response;
import models.author.Author;

public class BaseResponse {
    private Response response;

    public BaseResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }

    public String getHeader(String header) {
        return this.response.getHeader(header);
    }

    public String getBody() {
        return this.response.body().asString();
    }

    public Author getAsAuthorClass() {
        return this.response.body().as(Author.class);
    }
}
