package apiServiceTests.genre;

import apiServiceTests.BaseTest;
import config.Constants;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import static org.apache.http.HttpStatus.*;

@Feature("Genre Service Tests")
public class GenreTests extends BaseTest {

    @Test (description = "Positive check for creation a genre", groups = "clean_genre")
    public void verifyPostCreateGenre () {
        BaseResponse responseCreateEntity = genreService.createGenre(Constants.genre, Constants.genreRow, Constants.genrePositive);
        validator
                .validateStatusCode(responseCreateEntity.getStatusCode(),SC_CREATED);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.genre, GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateGenreResponse(responseCheckEntity,GenreService.body);
    }

    @Test (description = "Negative check for creation a genre")
    public void verifyPostErrorCreateGenre () {
        BaseResponse responseCreateEntity = genreService.createGenre(Constants.genre,Constants.genreRow, Constants.genreNegative);
        validator
                .validateStatusCode(responseCreateEntity.getStatusCode(),SC_BAD_REQUEST);
    }

    @Test (description = "Positive check for update genre", groups={"require_genre"})
    public void verifyPutUpdateGenre () {
        genreService
                .createGenre(Constants.genre,Constants.genreRow, Constants.genrePositive);

        BaseResponse responseUpdateEntity = genreService.updateGenre(Constants.genre,Constants.genreUpdateRow, GenreService.genreId, Constants.genrePositive);
        validator
                .validateStatusCode(responseUpdateEntity.getStatusCode(),SC_OK);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.genre, GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateGenreResponse(responseCheckEntity,GenreService.body);
    }

    @Test (description = "Positive check for delete genre", groups={"require_genre"})
    public void verifyDeleteGenre () {
        baseService
                .removeEntityForcibly(Constants.genre, GenreService.genreId, Constants.forcibly);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.genre, GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_NOT_FOUND);
    }

    @Test (description = "Positive check for get all genres with queryParameters", groups={"require_genre"})
    public void verifyGetAllGenresQueryParams () {
        BaseResponse responseGetEntities = baseService.getAllEntitiesWithOptions(Constants.genres, Constants.queryGenre);
        validator
                .validateStatusCode(responseGetEntities.getStatusCode(),SC_OK)
                .validateMultipleGenresResponseById(responseGetEntities,GenreService.body);
    }

    @Test (description = "Positive check for search genres", groups={"require_genre"})
    public void verifyGetSearchGenres () {
        BaseResponse responseSearchEntities = baseService.getAllEntitiesWithSearch(Constants.genres, Constants.searchGenre, Constants.search);
        validator
                .validateStatusCode(responseSearchEntities.getStatusCode(),SC_OK)
                .validateMultipleGenresResponseByName(responseSearchEntities);
    }

    @Test (description = "Positive check for get all genres of special Author", groups={"require_full_cleanup"})
    public void verifyGetGenresByAuthor () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(Constants.genres,Constants.author,AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateMultipleGenresResponseById(responseCheckEntity,GenreService.body);
    }

    @Test (description = "Positive check for get a genre of special Book", groups={"require_full_cleanup"})
    public void verifyGetGenresByBook () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(Constants.genre,Constants.book,BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateGenreResponse(responseCheckEntity,GenreService.body);
    }
}
