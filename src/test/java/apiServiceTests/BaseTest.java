package apiServiceTests;

import config.ServicesConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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
    public String entityGenre=ServicesConfig.GENRE.getSingular();
    public String entityGenres=ServicesConfig.GENRE.getPlural();
    public String entityBook=ServicesConfig.BOOK.getSingular();
    public String entityBooks=ServicesConfig.BOOK.getPlural();
    public String entityAuthor=ServicesConfig.AUTHOR.getSingular();
    public String entityAuthors=ServicesConfig.AUTHOR.getPlural();
    public String search=ServicesConfig.SEARCH.getSingular();
    public Logger LOG = Logger.getLogger(BaseTest.class);


    public int testBookRow = 0;
    public int testBookUpdateRow = 1;
    public int testGenreRow = 0;
    public int testGenreUpdateRow = 1;
    public int testAuthorRow = 0;
    public int testAuthorUpdateRow = 1;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        BasicConfigurator.configure();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
    }
}
