package apiServiceTests.book;

import apiServiceTests.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.*;
import service.AuthorService;
import service.BookService;
import service.GenreService;

public class BaseTestBook extends BaseTest {

    @BeforeTest(alwaysRun = true)
    public void beforeTest(final ITestContext testContext) {
        LOG.info("Test Case started: "+testContext.getName());
        authorService
                .createAuthor(entityAuthor,testBookRow, "authorPositive.csv");

        genreService
                .createGenre(entityGenre,testBookRow, "genrePositive.csv");
    }
    @AfterTest(alwaysRun = true)
    public void afterTest(final ITestContext testContext) {
        LOG.info("Test Case finished: "+testContext.getName());
        baseService
                .removeEntityForcibly(entityGenre, GenreService.genreId, "false");
        baseService
                .removeEntityForcibly(entityAuthor, AuthorService.authorId, "false");
    }


    @AfterGroups ("require_cleanup")
    public void afterGroup() {
        baseService
                .removeEntityForcibly(entityBook, BookService.bookId, "false");
    }
}
