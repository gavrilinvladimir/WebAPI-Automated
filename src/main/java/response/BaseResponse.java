package response;

import io.restassured.response.Response;
import models.author.Author;
import models.book.Book;
import models.genre.Genre;

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

    public String getHeader() {
        return this.response.headers().toString();
    }

    public String getContentType( ) {
        return this.response.getContentType();
    }
    public long getTime( ) {
        return this.response.getTime();
    }

    public String getBody() {
        return this.response.body().asString();
    }

    public Author getAsAuthorClass() {
        return this.response.body().as(Author.class);
    }

    public List<Author> getAsAuthorClassArray() {
        return Arrays.asList(response.getBody().as(Author[].class));
    }

    public Book getAsBookClass() {
        return this.response.body().as(Book.class);
    }

    public List<Book> getAsBookClassArray() {
        return Arrays.asList(response.getBody().as(Book[].class));
    }

    public Genre getAsGenreClass() {
        return this.response.body().as(Genre.class);
    }

    public List<Genre> getAsGenreClassArray() {
        return Arrays.asList(response.getBody().as(Genre[].class));
    }

}
