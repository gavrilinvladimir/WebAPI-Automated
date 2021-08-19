package utils;

import io.qameta.allure.Step;
import models.author.Author;
import models.book.Book;
import models.genre.Genre;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import java.util.List;

public class Validator {
    private static final Logger LOG = Logger.getLogger(Validator.class);
    public EndpointBuilder endpointBuilder = new EndpointBuilder();

    @Step ("Validate Status code")
    public  Validator validateStatusCode (int actualCode, int expectedCode) {
        Assert.assertEquals(actualCode,expectedCode,String.format(
                "\nStatus code is: '%s'.\nExpected: %s", actualCode, expectedCode));
        LOG.info("Status code is: "+actualCode);
        return this;
    }
    @Step ("Validate Author response")
    public Validator validateAuthorResponse (BaseResponse response, Author expected) {
        Author actual = response.getAsAuthorClass();
        SoftAssert soft = new SoftAssert();

        soft.assertEquals(expected.getAuthorId(),actual.getAuthorId(),String.format(
                "\n'authorId' Expected: %s.Actual: %s",expected.getAuthorId(),actual.getAuthorId()));
        soft.assertEquals(expected.getAuthorName().getFirst(),actual.getAuthorName().getFirst(),String.format(
                "\n'authorName'.'first' Expected: %s.Actual: %s",expected.getAuthorName().getFirst(),actual.getAuthorName().getFirst()));
        soft.assertEquals(expected.getAuthorName().getSecond(),actual.getAuthorName().getSecond(),String.format(
                "\n'authorName'.'second' Expected: %s.Actual: %s",expected.getAuthorName().getSecond(),actual.getAuthorName().getSecond()));
        soft.assertEquals(expected.getNationality(),actual.getNationality(),String.format(
                "\n'nationality' Expected: %s.Actual: %s",expected.getNationality(),actual.getNationality()));
        soft.assertEquals(expected.getBirth().getDate(),actual.getBirth().getDate(),String.format(
                "\n'birth'.'date' Expected: %s.Actual: %s",expected.getBirth().getDate(),actual.getBirth().getDate()));
        soft.assertEquals(expected.getBirth().getCountry(),actual.getBirth().getCountry(),String.format(
                "\n'birth'.'country' Expected: %s.Actual: %s",expected.getBirth().getCountry(),actual.getBirth().getCountry()));
        soft.assertEquals(expected.getBirth().getCity(),actual.getBirth().getCity(),String.format(
                "\n'birth'.'city' Expected: %s.Actual: %s",expected.getBirth().getCity(),actual.getBirth().getCity()));
        soft.assertEquals(expected.getAuthorDescription(),actual.getAuthorDescription(),String.format(
                "\n'authorDescription' Expected: %s.Actual: %s",expected.getAuthorDescription(),actual.getAuthorDescription()));
        soft.assertAll();
        LOG.info("Created Author is correct");
        return this;
    }

    @Step ("Check if Author is present in response")
    public  Validator validateMultipleAuthorsResponseById (BaseResponse response, Author expected) {
        List<Author> authors = response.getAsAuthorClassArray();
        int i = 0;
        for (Author author: authors) {
            if (author.getAuthorId().equals(expected.authorId)) i++;
        }
        Assert.assertEquals(i,1,String.format("Expected Author 'authorId=%s' is not present in queryResults",expected.authorId));
        LOG.info(String.format("Expected Author 'authorId=%s' is present in queryResults",expected.authorId));
        return this;
    }

    @Step ("Validate Genre response")
    public Validator validateGenreResponse (BaseResponse response, Genre expected) {
        Genre actual = response.getAsGenreClass();
        SoftAssert soft = new SoftAssert();

        soft.assertEquals(expected.getGenreId(),actual.getGenreId(),String.format(
                "\n'genreId' Expected: %s.Actual: %s",expected.getGenreId(),actual.getGenreId()));
        soft.assertEquals(expected.getGenreName(),actual.getGenreName(),String.format(
                "\n'genreName' Expected: %s.Actual: %s",expected.getGenreName(),actual.getGenreName()));
        soft.assertEquals(expected.getGenreDescription(),actual.getGenreDescription(),String.format(
                "\n'genreDescription' Expected: %s.Actual: %s",expected.getGenreDescription(),actual.getGenreDescription()));
        soft.assertAll();
        LOG.info("Created Genre is correct");
        return this;
    }

    @Step ("Check if Genre is present in response")
    public  Validator validateMultipleGenresResponseById (BaseResponse response, Genre expected) {
        List<Genre> genres = response.getAsGenreClassArray();
        int i = 0;
        for (Genre genre: genres) {
            if (genre.getGenreId().equals(expected.genreId)) i++;
        }
        Assert.assertEquals(i,1,String.format("Expected Genre 'genreId=%s' is not present in queryResults",expected.genreId));
        LOG.info(String.format("Expected Genre 'genreId=%s' is present in queryResults",expected.genreId));
        return this;
    }

    @Step ("Validate Book response")
    public Validator validateBookResponse (BaseResponse response) {
        Book expected = BookService.body;
        Book actual = response.getAsBookClass();
        SoftAssert soft = new SoftAssert();

        soft.assertEquals(expected.getBookId(),actual.getBookId(),String.format(
                "\n'bookId' Expected: %s.Actual: %s",expected.getBookId(),actual.getBookId()));
        soft.assertEquals(expected.getBookName(),actual.getBookName(),String.format(
                "\n'bookName' Expected: %s.Actual: %s",expected.getBookName(),actual.getBookName()));
        soft.assertEquals(expected.getBookLanguage(),actual.getBookLanguage(),String.format(
                "\n'bookLanguage' Expected: %s.Actual: %s",expected.getBookLanguage(),actual.getBookLanguage()));
        soft.assertEquals(expected.getBookDescription(),actual.getBookDescription(),String.format(
                "\n'bookDescription' Expected: %s.Actual: %s",expected.getBookDescription(),actual.getBookDescription()));
        soft.assertEquals(expected.getAdditional().getPageCount(),actual.getAdditional().getPageCount(),String.format(
                "\n'additional'.'pageCount' Expected: %s.Actual: %s",expected.getAdditional().getPageCount(),actual.getAdditional().getPageCount()));
        soft.assertEquals(expected.getAdditional().getSize().getHeight(),actual.getAdditional().getSize().getHeight(),String.format(
                "\n'additional'.'size'.'height' Expected: %s.Actual: %s",expected.getAdditional().getSize().getHeight(),actual.getAdditional().getSize().getHeight()));
        soft.assertEquals(expected.getAdditional().getSize().getWidth(),actual.getAdditional().getSize().getWidth(),String.format(
                "\n'additional'.'size'.'width' Expected: %s.Actual: %s",expected.getAdditional().getSize().getWidth(),actual.getAdditional().getSize().getWidth()));
        soft.assertEquals(expected.getAdditional().getSize().getLength(),actual.getAdditional().getSize().getLength(),String.format(
                "\n'additional'.'size'.'length' Expected: %s.Actual: %s",expected.getAdditional().getSize().getLength(),actual.getAdditional().getSize().getLength()));
        soft.assertEquals(expected.getPublicationYear(),actual.getPublicationYear(),String.format(
                "\n'publicationYear' Expected: %s.Actual: %s",expected.getPublicationYear(),actual.getPublicationYear()));
        soft.assertAll();
        LOG.info("Created Book is correct");
        return this;
    }

    @Step ("Check if Book is present in response")
    public  Validator validateMultipleBooksResponseById (BaseResponse response) {
        Book expected = BookService.body;
        List<Book> books = response.getAsBookClassArray();
        int i = 0;
        for (Book book: books) {
            if (book.getBookId().equals(expected.bookId)) i++;
        }
        Assert.assertEquals(i,1,String.format("Expected Book 'bookId=%s' is not present in queryResults",expected.bookId));
        LOG.info(String.format("Expected Book 'bookId=%s' is present in queryResults",expected.bookId));
        return this;
    }

    @Step ("Check if Book is present in response")
    public  Validator validateMultipleBooksResponseByName (BaseResponse response) {
        List<Book> books = response.getAsBookClassArray();
        int count = 0;
        for (Book book: books) {
            if (book.getBookName().contains(endpointBuilder.queryOptions.get("q"))) count++;
        }
        Assert.assertTrue(count>=1,String.format("Expected Book 'bookName=%s' is not present in queryResults",endpointBuilder.queryOptions.get("q")));
        LOG.info(String.format("Expected Book 'bookName=%s' is present in queryResults",endpointBuilder.queryOptions.get("q")));
        return this;
    }

    @Step ("Check if Genre is present in response")
    public  Validator validateMultipleGenresResponseByName (BaseResponse response) {
        List<Genre> genres = response.getAsGenreClassArray();
        int i = 0;
        for (Genre genre: genres) {
            if (genre.getGenreName().contains(endpointBuilder.queryOptions.get("query"))) i++;
        }
        Assert.assertTrue(i>=1,String.format("Expected Genre 'genreName=%s' is not present in queryResults",endpointBuilder.queryOptions.get("query")));
        LOG.info(String.format("Expected Genre 'genreName=%s' is present in queryResults",endpointBuilder.queryOptions.get("query")));
        return this;
    }

    @Step ("Check if Author with is present in response")
    public  Validator validateMultipleAuthorsResponseByName (BaseResponse response) {
        List<Author> authors = response.getAsAuthorClassArray();
        int i = 0;
        for (Author genre: authors) {
            if (genre.getAuthorName().getFirst().contains(endpointBuilder.queryOptions.get("query"))
                    |genre.getAuthorName().getSecond().contains(endpointBuilder.queryOptions.get("query"))) i++;
        }
        Assert.assertTrue(i>=1,String.format("Expected Author 'authorName=%s' is not present in queryResults",endpointBuilder.queryOptions.get("query")));
        LOG.info(String.format("Expected Author 'authorName=%s' is present in queryResults",endpointBuilder.queryOptions.get("query")));
        return this;
    }
}
