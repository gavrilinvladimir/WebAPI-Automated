package apiServiceTests.genre;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;

import static org.apache.http.HttpStatus.*;

@Feature("Genre Service Tests")
public class GenreTests extends BaseTestGenre {

    @Test (description = "Positive check for creation a genre", groups={"require_cleanup"})
    public void verifyPostCreateGenre () {
        BaseResponse responseCreateEntity = genreService.createGenre(entityGenre,testGenreRow, "genrePositive.csv");
        validator
                .validateStatusCode(responseCreateEntity,SC_CREATED);

        BaseResponse responseCheckEntity = baseService.getEntityById(entityGenre, GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateGenreResponse(responseCheckEntity);
    }

    @Test (description = "Negative check for creation a genre")
    public void verifyPostErrorCreateGenre () {
        BaseResponse responseCreateEntity = genreService.createGenre(entityGenre,testGenreRow, "genreNegative.csv");
        validator
                .validateStatusCode(responseCreateEntity,SC_BAD_REQUEST);
    }

    @Test (description = "Positive check for update genre", groups={"require_cleanup"})
    public void verifyPutUpdateGenre () {
        genreService
                .createGenre(entityGenre,testGenreRow, "genrePositive.csv");

        BaseResponse responseUpdateEntity = genreService.updateGenre(entityGenre,testGenreUpdateRow, GenreService.genreId, "genrePositive.csv");
        validator
                .validateStatusCode(responseUpdateEntity,SC_OK);

        BaseResponse responseCheckEntity = baseService.getEntityById(entityGenre, GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateGenreResponse(responseCheckEntity);
    }

    @Test (description = "Positive check for delete genre", groups={"require_cleanup"})
    public void verifyDeleteGenre () {
        genreService
                .createGenre(entityGenre,testGenreRow, "genrePositive.csv");

        baseService
                .removeEntityForcibly(entityGenre, GenreService.genreId, "false");

        BaseResponse responseCheckEntity = baseService.getEntityById(entityGenre, GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity,SC_NOT_FOUND);
    }

    @Test (description = "Positive check for get all genres with queryParameters", groups={"require_cleanup"})
    public void verifyGetAllGenresQueryParams () {
        genreService.
                createGenre(entityGenre,testGenreRow, "genrePositive.csv");
        BaseResponse responseGetEntities = baseService.getAllEntitiesWithOptions(entityGenres, "testData/queryOptionsGenre.json");
        validator
                .validateStatusCode(responseGetEntities,SC_OK)
                .validateMultipleGenresResponseById(responseGetEntities);
    }

    @Test (description = "Positive check for search genres", groups={"require_cleanup"})
    public void verifyGetSearchGenres () {
        genreService.
                createGenre(entityGenre,testGenreRow, "genrePositive.csv");

        BaseResponse responseSearchEntities = baseService.getAllEntitiesWithSearch(entityGenres, "testData/searchGenre.json", search);
        validator
                .validateStatusCode(responseSearchEntities,SC_OK)
                .validateMultipleGenresResponseByName(responseSearchEntities);
    }

    @Test (description = "Positive check for get all genres of special Author", groups={"require_full_cleanup"})
    public void verifyGetGenresByAuthor () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(entityGenres,entityAuthor,AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateMultipleGenresResponseById(responseCheckEntity);
    }

    @Test (description = "Positive check for get a genre of special Book", groups={"require_full_cleanup"})
    public void verifyGetGenresByBook () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(entityGenre,entityBook,BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateGenreResponse(responseCheckEntity);
    }
}
