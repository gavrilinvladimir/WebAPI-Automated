package response;

import io.restassured.response.Response;
import models.author.Author;
import java.util.Arrays;
import java.util.List;

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

    public List<Author> getAsAuthorClassArray() {
        return Arrays.asList(response.getBody().as(Author.class));
    }
}
