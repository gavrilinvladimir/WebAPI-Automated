package apiServiceTests;

import config.Constants;
import io.qameta.allure.Description;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;
import service.AuthorService;
import service.BaseService;
import service.BookService;
import service.GenreService;
import utils.Validator;

public class BaseTest {
    public BaseService baseService = new BaseService();
    public Validator validator = new Validator();
    public BookService bookService = new BookService();
    public AuthorService authorService = new AuthorService();
    public GenreService genreService = new GenreService();
    public Logger LOG = Logger.getLogger(BaseTest.class);



    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        BasicConfigurator.configure();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
    }

    @BeforeMethod (onlyForGroups ={"require_author"}, description ="Pre-conditions: create Author" )
    public void beforeGroupAuthor() {
        authorService
                .createAuthor(Constants.author,Constants.authorRow, Constants.authorPositive);
    }

    @AfterMethod (onlyForGroups ={"require_author","clean_author"}, description ="Post-conditions: remove Author")
    public void afterGroupAuthor() {
        baseService
                .removeEntityForcibly(Constants.author, AuthorService.authorId, Constants.forcibly);
    }

    @BeforeMethod (onlyForGroups ={"require_book"}, description ="Pre-conditions: create Book")
    public void beforeGroupBook() {
        bookService
                .createBook(Constants.book, Constants.bookRow,AuthorService.authorId,GenreService.genreId, Constants.bookPositive);
    }

    @AfterMethod (onlyForGroups ={"require_book","clean_book"}, description ="Post-conditions: remove Book")
    public void afterGroupBook() {
        baseService
                .removeEntityForcibly(Constants.book, BookService.bookId, Constants.forcibly);
    }

    @BeforeMethod (onlyForGroups ={"require_genre"}, description ="Pre-conditions: create Genre")
    public void beforeGroupGenre() {
        genreService
                .createGenre(Constants.genre,Constants.genreRow, Constants.genrePositive);
    }

    @AfterMethod (onlyForGroups ={"require_genre","clean_genre"}, description ="Post-conditions: remove Genre")
    public void afterGroupGenre() {
        baseService
                .removeEntityForcibly(Constants.genre, GenreService.genreId, Constants.forcibly);
    }

    @BeforeMethod(onlyForGroups ={"require_full_cleanup"}, description ="Pre-conditions: create Author, Genre and Book")
    public void beforeMethodFull(final ITestContext testContext) {
        LOG.info("Test Case started: "+testContext.getName());
        authorService
                .createAuthor(Constants.author,Constants.authorRow, Constants.authorPositive);
        genreService
                .createGenre(Constants.genre,Constants.genreRow, Constants.genrePositive);
        bookService
                .createBook(Constants.book, Constants.bookRow,AuthorService.authorId,GenreService.genreId, Constants.bookPositive);
    }
    @AfterMethod(onlyForGroups ={"require_full_cleanup"}, description ="Post-conditions: remove Book, Genre and Author")
    public void afterMethodFull(final ITestContext testContext) {
        LOG.info("Test Case finished: "+testContext.getName());
        baseService
                .removeEntityForcibly(Constants.book, BookService.bookId, Constants.forcibly);
        baseService
                .removeEntityForcibly(Constants.genre, GenreService.genreId, Constants.forcibly);
        baseService
                .removeEntityForcibly(Constants.author, AuthorService.authorId, Constants.forcibly);
    }
}
