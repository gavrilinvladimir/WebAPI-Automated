package apiServiceTests.author;

import apiServiceTests.BaseTest;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import service.AuthorService;
import service.BookService;
import service.GenreService;


public class BaseTestAuthor extends BaseTest {

    @BeforeTest(alwaysRun = true)
    @Description ("Just console log Start")
    public void beforeTest(final ITestContext testContext) {
        LOG.info("Test Case started: "+testContext.getName());
    }

    @AfterTest(alwaysRun = true)
    @Description ("Just console log Finish")
    public void afterTest(final ITestContext testContext) {
        LOG.info("Test Case finished: "+testContext.getName());
    }

    @BeforeGroups("require_full_cleanup")
    @Description ("Pre-conditions: Create Author, Book and Genre")
    public void beforeGroupWithBook(final ITestContext testContext) {
        authorService
                .createAuthor(entityAuthor,0, "authorPositive.csv");
        genreService
                .createGenre(entityGenre,testGenreRow, "genrePositive.csv");
        bookService
                .createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");
    }

    @AfterGroups ("require_cleanup")
    @Description ("Post-conditions: remove Author")
    public void afterGroup() {
        baseService
                .removeEntityForcibly(entityAuthor, AuthorService.authorId, "false");
    }

    @AfterGroups("require_full_cleanup")
    @Description ("Post-conditions: remove Book, Author and Genre")
    public void afterGroupWithAuthor(final ITestContext testContext) {
        baseService
                .removeEntityForcibly(entityBook, BookService.bookId, "false");
        baseService
                .removeEntityForcibly(entityAuthor, AuthorService.authorId, "false");
        baseService
                .removeEntityForcibly(entityGenre, GenreService.genreId, "false");
    }
}
