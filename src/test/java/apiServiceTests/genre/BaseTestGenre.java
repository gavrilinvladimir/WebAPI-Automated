package apiServiceTests.genre;

import apiServiceTests.BaseTest;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import service.AuthorService;
import service.BookService;
import service.GenreService;

public class BaseTestGenre extends BaseTest {

    @BeforeTest(alwaysRun = true)
    public void beforeTest(final ITestContext testContext) {
        LOG.info("Test Case started: "+testContext.getName());
    }
    @AfterTest(alwaysRun = true)
    public void afterTest(final ITestContext testContext) {
        LOG.info("Test Case finished: "+testContext.getName());
    }

    @BeforeGroups("require_full_cleanup")
    public void beforeGroupWithBook() {
        authorService
                .createAuthor(entityAuthor,0, "authorPositive.csv");
        genreService
                .createGenre(entityGenre,testGenreRow, "genrePositive.csv");
        bookService
                .createBook(entityBook,testBookRow,AuthorService.authorId,GenreService.genreId, "bookPositive.csv");
    }

    @AfterGroups ("require_cleanup")
    public void afterGroup() {
        baseService
                .removeEntityForcibly(entityGenre, GenreService.genreId, "false");
    }

    @AfterGroups("require_full_cleanup")
    public void afterGroupWithAuthor() {
        baseService
                .removeEntityForcibly(entityBook, BookService.bookId, "false");
        baseService
                .removeEntityForcibly(entityAuthor, AuthorService.authorId, "false");
        baseService
                .removeEntityForcibly(entityGenre, GenreService.genreId, "false");
    }
}
