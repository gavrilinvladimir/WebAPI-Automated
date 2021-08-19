package apiServiceTests.book;

import apiServiceTests.BaseTest;
import config.Constants;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import static org.apache.http.HttpStatus.*;


@Feature("Book Service Tests")
public class BookTests extends BaseTest {

    @Test (description = "Positive check for creation a book", groups={"require_author", "require_genre", "clean_book"})
    public void verifyPostCreateBook () {
        BaseResponse responseCreateEntity = new BookService()
                .createBook(Constants.book, Constants.bookRow,AuthorService.authorId,GenreService.genreId, Constants.bookPositive);
        validator
                .validateStatusCode(responseCreateEntity.getStatusCode(),SC_CREATED)
                .validateBookResponse(responseCreateEntity);
    }

    @Test (description = "Negative check for creation a book", groups={"require_author", "require_genre"})
    public void verifyPostErrorCreateBook () {
        BaseResponse responseCreateEntity = bookService.createBook(Constants.book,Constants.bookRow,AuthorService.authorId,GenreService.genreId, Constants.bookNegative);
        validator
                .validateStatusCode(responseCreateEntity.getStatusCode(),SC_BAD_REQUEST);
    }

    @Test (description = "Positive check for update book", groups={"require_full_cleanup"})
    public void verifyPutUpdateBook () {
        BaseResponse responseUpdateEntity = bookService.updateBook(Constants.book,Constants.bookUpdateRow, BookService.bookId, Constants.bookPositive);
        validator
                .validateStatusCode(responseUpdateEntity.getStatusCode(),SC_OK);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.book, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateBookResponse(responseCheckEntity);
    }

    @Test (description = "Positive check for delete book", groups={"require_full_cleanup"})
    public void verifyDeleteBook () {
        baseService
                .removeEntityForcibly(Constants.book, BookService.bookId, Constants.forcibly);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.book, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_NOT_FOUND);
    }

    @Test (description = "Positive check for get all books with queryParameters", groups={"require_full_cleanup"})
    public void verifyGetAllBooksQueryParams () {
        bookService.
                createBook(Constants.book,Constants.bookRow,AuthorService.authorId,GenreService.genreId, Constants.bookPositive);
        BaseResponse responseGetEntities = baseService.getAllEntitiesWithOptions(Constants.books, Constants.queryBook);
        validator
                .validateStatusCode(responseGetEntities.getStatusCode(),SC_OK)
                .validateMultipleBooksResponseById(responseGetEntities);
    }

    @Test (description = "Positive check for search books", groups={"require_author", "require_genre"})
    public void verifyGetSearchBooks () {
        bookService.
                createBook(Constants.book,Constants.bookRow,AuthorService.authorId,GenreService.genreId, Constants.bookPositive);

        BaseResponse responseSearchEntities = baseService.getAllEntitiesWithSearch(Constants.books, Constants.searchBook, Constants.search);
        validator
                .validateStatusCode(responseSearchEntities.getStatusCode(),SC_OK)
                .validateMultipleBooksResponseByName(responseSearchEntities);
    }

    @Test (description = "Positive check for get all Books of special Author", groups={"require_full_cleanup"})
    public void verifyGetBooksByAuthor () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(Constants.books,Constants.author,AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateMultipleBooksResponseById(responseCheckEntity);
    }

    @Test (description = "Positive check for get all Books of special Author in special Genre", groups={"require_full_cleanup"})
    public void verifyGetBooksByAuthorByGenre () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinTwoAnother(Constants.books,Constants.author,AuthorService.authorId,Constants.genre,GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateMultipleBooksResponseById(responseCheckEntity);
    }

}
