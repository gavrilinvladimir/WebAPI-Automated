package apiServiceTests.book;

import apiServiceTests.book.BaseTestBook;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import static org.apache.http.HttpStatus.*;


@Feature("Book Service Tests")
public class BookTests extends BaseTestBook {

    @Test (description = "Positive check for creation a book", groups={"require_cleanup"})
    public void verifyPostCreateBook () {
        BaseResponse responseCreateEntity = new BookService().createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");
        validator
                .validateStatusCode(responseCreateEntity,SC_CREATED);

        BaseResponse responseCheckEntity = baseService.getEntityById(entityBook, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateBookResponse(responseCheckEntity);
    }

    @Test (description = "Negative check for creation a book")
    public void verifyPostErrorCreateBook () {
        BaseResponse responseCreateEntity = bookService.createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookNegative.csv");
        validator
                .validateStatusCode(responseCreateEntity,SC_BAD_REQUEST);
    }

    @Test (description = "Positive check for update book", groups={"require_cleanup"})
    public void verifyPutUpdateBook () {
        BaseResponse responseCreateEntity = bookService.createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");
        validator
                .validateStatusCode(responseCreateEntity,SC_CREATED);

        BaseResponse responseUpdateEntity = bookService.updateBook(entityBook,testBookUpdateRow, BookService.bookId, "bookPositive.csv");
        validator
                .validateStatusCode(responseUpdateEntity,SC_OK);

        BaseResponse responseCheckEntity = baseService.getEntityById(entityBook, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateBookResponse(responseCheckEntity);
    }

    @Test (description = "Positive check for delete book")
    public void verifyDeleteBook () {
        BaseResponse responseCreateEntity = bookService.createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");
        validator
                .validateStatusCode(responseCreateEntity,SC_CREATED);

        baseService
                .removeEntityForcibly(entityBook, BookService.bookId, "false");

        BaseResponse responseCheckEntity = baseService.getEntityById(entityBook, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity,SC_NOT_FOUND);
    }

    @Test (description = "Positive check for get all books with queryParameters", groups={"require_cleanup"})
    public void verifyGetAllBooksQueryParams () {
        bookService.
                createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");
        BaseResponse responseGetEntities = baseService.getAllEntitiesWithOptions(entityBooks, "testData/queryOptionsBook.json");
        validator
                .validateStatusCode(responseGetEntities,SC_OK)
                .validateMultipleBooksResponseById(responseGetEntities);
    }

    @Test (description = "Positive check for search books", groups={"require_cleanup"})
    public void verifyGetSearchBooks () {
        bookService.
                createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");

        BaseResponse responseSearchEntities = baseService.getAllEntitiesWithSearch(entityBooks, "testData/searchBook.json", search);
        validator
                .validateStatusCode(responseSearchEntities,SC_OK)
                .validateMultipleBooksResponseByName(responseSearchEntities);
    }

    @Test (description = "Positive check for get all Books of special Author", groups={"require_cleanup"})
    public void verifyGetBooksByAuthor () {
        bookService.
                createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");

        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(entityBooks,entityAuthor,AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateMultipleBooksResponseById(responseCheckEntity);
    }

    @Test (description = "Positive check for get all Books of special Author in special Genre", groups={"require_cleanup"})
    public void verifyGetBooksByAuthorByGenre () {
        bookService.
                createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");

        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinTwoAnother(entityBooks,entityAuthor,AuthorService.authorId,entityGenre,GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateMultipleBooksResponseById(responseCheckEntity);
    }

}
